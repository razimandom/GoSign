package viewDoc.portlet;

import viewDoc.constants.ViewDocPortletKeys;

import com._42Penguins.gosign.model.EntDoc;
import com._42Penguins.gosign.model.EntFileUpload;
import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Blob;
import java.util.Base64;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raziman Dom
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=viewDoc Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ViewDocPortletKeys.ViewDoc,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ViewDocPortlet extends MVCPortlet {
	
	public void doAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		
		try {
			
			long docId = ParamUtil.getLong(actionRequest, "docId");
			String doAction = ParamUtil.getString(actionRequest, "doAction");
			EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
			String encodedSign = doc.getDoc_signature();
			System.out.println(encodedSign);
			
			String actionVerify = "verify";
			String actionUpdate = "update";
			String actionDel = "delete";
			String actionBack = "back";
			
			if (doAction.equals(actionVerify)){
				
				if(encodedSign.isEmpty()){
					System.out.println("No signature");
					SessionErrors.add(actionRequest, "error-key-nosign");
					actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
					
				}else{
					doVerifySign(actionRequest, actionResponse);
				}
				
			} else if (doAction.equals(actionDel)){
				doDelDoc(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
				
			} else if (doAction.equals(actionBack)){
				doBack(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
				
			} else if (doAction.equals(actionUpdate)){
				doUpdateDoc(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			} else {
				SessionErrors.add(actionRequest, "error-key-invalidAction");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			}
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 
	 * Verify signature
	 * 
	 */
	
	public void doVerifySign(ActionRequest actionRequest, ActionResponse actionResponse) {

			//==> Retrieve data from input field
			String encodedPubKey = ParamUtil.getString(actionRequest, "input_pubkey");
			
			//==> This is standard ECC public key length
			int eccPubKeyLength = 88;
			
			//==> Check public key input from user
			if(encodedPubKey.length() == 0){
				SessionErrors.add(actionRequest, "error-key-null");
			
			//==>Proceed for signature verification if correct ECC public key format
			}else if (encodedPubKey.length() == eccPubKeyLength){
				
				try {
					
					//==> Retrieve data from database for current user from document table
					System.out.println("Retrieving data from database..."); 
					long docId = ParamUtil.getLong(actionRequest, "docId");
					EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
					String encodedSign = doc.getDoc_signature();
					String req_md5 = doc.getDoc_md5();
					
					//==> Decode signature and public key
					System.out.println("Decoding signature and public key...");  
					byte[] decodedSign = Base64.getDecoder().decode(encodedSign);
					byte[] decodedPubKey = Base64.getDecoder().decode(encodedPubKey);			
					
					//==> Get raw public key to verify signature
					System.out.println("Retrieving raw public key...");
					KeyFactory kf = KeyFactory.getInstance("EC");
					PublicKey rawPubKey = kf.generatePublic(new X509EncodedKeySpec(decodedPubKey));
					
					//==>Verify signature
					System.out.println("Verify Signature...");                
					Signature signature = Signature.getInstance("SHA1withECDSA");   
					byte[] bytes = req_md5.getBytes("UTF8");
					signature.initVerify(rawPubKey);
					signature.update(bytes);
					
					//==>If else message after verify key
					if(signature.verify(decodedSign)){
						SessionMessages.add(actionRequest, "request_processed", "Verification completed! This is valid signature.");
					}else{
						SessionErrors.add(actionRequest, "error-key");
					}
					
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SignatureException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}else{
				SessionErrors.add(actionRequest, "error-key-invalidECCPubKey");
				
			}
			System.out.println("Verification completed!");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
	}
	
	/*
	 * 
	 * Delete document method
	 * 
	 */
	
	public void doDelDoc(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		long docId = ParamUtil.getLong(actionRequest, "docId");
		long fileId = ParamUtil.getLong(actionRequest, "fileId");
		try {
			EntDocLocalServiceUtil.deleteEntDoc(docId);
			EntFileUploadLocalServiceUtil.deleteEntFileUpload(fileId);
			System.out.println("Document " + docId + "has been deleted");
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
		}
	}

	public void doBack(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws IOException, PortletException {
		
		System.out.println("Go back to view page.");
		actionResponse.setRenderParameter("mvcPath", "/view.jsp");
		
	}
	
	public void doUpdateDoc(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		
		System.out.println("================Start updateDoc=================");
		try{
			System.out.println("Updating deadline...");
			Long docId = ParamUtil.getLong(actionRequest, "docId");
			//Long fileId = ParamUtil.getLong(actionRequest, "docId");
			String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
			
			EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
			doc.setDoc_deadline(doc_deadline);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			System.out.println("New deadline is " + doc_deadline);
			System.out.println("Inserting data to DB");
			
			doc = EntDocLocalServiceUtil.updateEntDoc(doc);
			
		} catch (Exception e){
			System.out.println("Fail to update deadline...");
			e.printStackTrace();
		}
		System.out.println("================End updateDoc=================");
	}
	
	/**
     * Here serveResource method is used for displaying blob data
     */
    @Override
    public void serveResource(ResourceRequest resourceRequest,
            ResourceResponse resourceResponse) throws IOException,
            PortletException {
 
        try {
            long fileId = ParamUtil.getLong(resourceRequest, "fileId");
            
            EntFileUpload fileup = EntFileUploadLocalServiceUtil.getEntFileUpload(fileId);

            if (fileup != null) {
            	
            	System.out.println("hello");
                Blob blob = fileup.getFile_blob();
                byte[] binaryData = blob.getBytes(1, (int) blob.length());
                // resourceResponse.setContentType(blobDemo.getMimeType());
                resourceResponse.setContentType("application/application-download");
                resourceResponse.setProperty("Content-disposition","attachement; filename=" + fileup.getFile_name());
                OutputStream o = resourceResponse.getPortletOutputStream();
                o.write(binaryData);
                o.flush();
                o.close();
                resourceResponse.flushBuffer();
            }
 
        } catch (Exception e) {
 
        }
    }
	
	
}