package viewDoc.portlet;

import viewDoc.constants.ViewDocPortletKeys;
import com._42Penguins.gosign.model.EntDoc;
import com._42Penguins.gosign.model.EntFileUpload;
import com._42Penguins.gosign.model.EntKey;
import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil;
import com._42Penguins.gosign.service.EntKeyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
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
	
	private static Log _log = LogFactoryUtil.getLog(ViewDocPortlet.class);
	
	/**
	 * Action method. Redirect action from user to certain action.
	 * @param actionRequest
	 * @param actionResponse
	 * @throws PortalException
	 */
	
	public void doActionMethod(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException {
		
		_log.info("###################################################");
		_log.info("#               doActionMethod log                #");
		_log.info("###################################################");
		_log.info("START: doActionMethod Function");
		
		try {
			
			/*
			 *  Initiate variables
			 */
			
			_log.info("Fetch current document data");
			long docId = ParamUtil.getLong(actionRequest, "docId");
			String doAction = ParamUtil.getString(actionRequest, "doAction");
			EntDoc docData = EntDocLocalServiceUtil.getEntDoc(docId);
			String encodedSign = docData.getDoc_signature();
			String doc_status = docData.getDoc_status();
			
			/*
			 * List of status and action
			 */
			
			String statusPending = "Pending";
			String statusSigned = "Signed";
			String statusVerified = "Verified";
			String statusReject = "Rejected";
			String statusJustify = "Justify";
			String statusExpired = "Expired";
			
			String actionVerify = "verify";
			String actionUpdate = "update";
			String actionDel = "delete";
			String actionShowKey ="showkey";
			String actionBack ="back";
			
			/*
			 * Validation before redirect to verify, update, delete, and showkey method
			 */
			
			_log.info("Verify requested action: Verify/Update/Delete/Showkey");
			
			if (doAction.equals(actionVerify)){
				
				/*
				 * Start verify action validation
				 */
				
				if (doc_status.equals(statusSigned)) {
					
					_log.info("Retrieve signer signature");
					
					/*
					 * Start verify action
					 */
					
					if(encodedSign.isEmpty()){
						_log.warn("No signature found");
						SessionErrors.add(actionRequest, "error-key-nosign");
						actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
						
					}else{
						_log.info("Signature found. Verifying document docId: " + docId);
						doVerifySign(actionRequest, actionResponse);
						_log.info("Verified");
					}
					
				} else if (doc_status.equals(statusVerified)){
					_log.warn("Document already verified");
					SessionErrors.add(actionRequest, "error-verify-fail-verified");
					actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
					
				} else if (doc_status.equals(statusPending) || doc_status.equals(statusJustify)){
					_log.warn("Document need to be sign first before verify");
					SessionErrors.add(actionRequest, "error-verify-fail-no-sign");
					actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
					
				} else if (doc_status.equals(statusReject) || doc_status.equals(statusExpired)){
					_log.warn("Cannot verify document that was expired/rejected");
					SessionErrors.add(actionRequest, "error-verify-fail-rejected-expired");
					actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
					
				} else {
					_log.error("System unable to identify requested action");
					SessionErrors.add(actionRequest, "error-verify-fail-no-action");
					actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
					
				}
				
				/*
				 * Start delete action
				 */
				
			} else if (doAction.equals(actionDel)){
				

				_log.info("Deleting document docId: " + docId);
				doDelDoc(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mvcPath", "/view.jsp");
			
				/*
				 * Start update action validation
				 */
			
			} else if (doAction.equals(actionUpdate)){
				_log.info("Updating document docId: " + docId);
				doUpdateDoc(actionRequest, actionResponse);
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
				
				/*
				 * Start back action
				 */
				
			} else if (doAction.equals(actionBack)){
				actionResponse.setRenderParameter("mvcPath", "/view.jsp");
				
				/*
				 * Start show key action
				 */
				
			} else if (doAction.equals(actionShowKey)){
				
				doShowKey(actionRequest, actionResponse);
				
			} else {
				SessionErrors.add(actionRequest, "error-key-invalidAction");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
				_log.error("System unable to identify requested action");
			}
				
			
		} catch (IOException e) {
			_log.error("Error on doActionMethod");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortletException e) {
			_log.error("Error on doActionMethod");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Verify signature method
	 * @param actionRequest
	 * @param actionResponse
	 */
	
	public void doVerifySign(ActionRequest actionRequest, ActionResponse actionResponse) {
			
		_log.info("###################################################");
		_log.info("#           Sign Document Function log            #");
		_log.info("###################################################");
		_log.info("START: Sign Document Function");	
		
			//==> Retrieve data from input field
			String encodedPubKey = ParamUtil.getString(actionRequest, "input_pubkey");
			
			//==> This is standard ECC public key length
			int eccPubKeyLength = 88;
			
			_log.info("Validating public key format");
			
			//==> Check public key input from user
			if(encodedPubKey.length() == 0){
				_log.error("Public key does not exist");
				SessionErrors.add(actionRequest, "error-key-null");
			
			//==>Proceed for signature verification if correct ECC public key format
			}else if (encodedPubKey.length() == eccPubKeyLength){
				
				try {
					
					_log.info("Verifying document");
					
					//==> Retrieve data from database for current user from document table
					_log.info("Retrieving signer's signature data from database..."); 
					long docId = ParamUtil.getLong(actionRequest, "docId");
					EntDoc docData = EntDocLocalServiceUtil.getEntDoc(docId);
					String encodedSign = docData.getDoc_signature();
					String req_md5 = docData.getDoc_md5();
					String sign_name = docData.getSign_name();
					
					//==> Decode signature and public key
					_log.info("Decoding signature and public key");  
					byte[] decodedSign = Base64.getDecoder().decode(encodedSign);
					byte[] decodedPubKey = Base64.getDecoder().decode(encodedPubKey);			
					
					//==> Get raw public key to verify signature
					_log.info("Preparing public key for signature verification");
					KeyFactory kf = KeyFactory.getInstance("EC");
					PublicKey rawPubKey = kf.generatePublic(new X509EncodedKeySpec(decodedPubKey));
					
					//==>Verify signature
					_log.info("Verifying signature");                
					Signature signature = Signature.getInstance("SHA1withECDSA");   
					byte[] bytes = req_md5.getBytes("UTF8");
					signature.initVerify(rawPubKey);
					signature.update(bytes);
					
					//==>If else message after verify key

					if(signature.verify(decodedSign)){
						_log.info("Document has been verified");
						SessionMessages.add(actionRequest, "request_processed", "Verification completed! This document has been signed by " + sign_name);
						doStatusVerify(actionRequest, actionResponse);
					}else{
						_log.error("Verification failed! System unable to verify using provided public key and signature");
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
					_log.error("Verification failed! System unable to verify using provided public key and signature");
					e.printStackTrace();
				}
		
			}else{
				_log.error("Invalid public key");
				SessionErrors.add(actionRequest, "error-key-invalidECCPubKey");
				
			}
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
	}
	
	
	/**
	 * Verify signature method
	 * @param actionRequest
	 * @param actionResponse
	 */
	
	
	public void doStatusVerify(ActionRequest actionRequest, ActionResponse actionResponse) {
		
		_log.info("###################################################");
		_log.info("#            Verify Status Document log           #");
		_log.info("###################################################");
		_log.info("START: Update Status Function");
		
		try{
			_log.info("Updating status to verified");
			Long docId = ParamUtil.getLong(actionRequest, "docId");
			String doc_status = "Verified";
			
			EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
			doc.setDoc_status(doc_status);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			_log.info("Inserting data to DB");
			
			doc = EntDocLocalServiceUtil.updateEntDoc(doc);
			
			SessionMessages.add(actionRequest, "request_processed", "Successfully verified!");
			
			_log.info("COMPLETED: Verified document: " + docId);
			
		} catch (Exception e){
			_log.error("Error update status to verified");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Delete method
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	
	public void doDelDoc(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		
		_log.info("###################################################");
		_log.info("#          Delete Document Function log           #");
		_log.info("###################################################");
		_log.info("START: Delete Document Function");
		
		long docId = ParamUtil.getLong(actionRequest, "docId");
		long fileId = ParamUtil.getLong(actionRequest, "fileId");
		
		try {
			_log.info("Deleting document" );
			EntDocLocalServiceUtil.deleteEntDoc(docId);
			EntFileUploadLocalServiceUtil.deleteEntFileUpload(fileId);
			_log.info("COMPLETED: Deleted document: " + docId);
			SessionMessages.add(actionRequest, "request_processed", "Deleted req Id: " + docId);
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update method
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	
	public void doUpdateDoc(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		
		_log.info("###################################################");
		_log.info("#          Update Document Function log           #");
		_log.info("###################################################");
		_log.info("START: Update Document Function");
		
		try{
			_log.info("Updating deadline.");
			Long docId = ParamUtil.getLong(actionRequest, "docId");
			//Long fileId = ParamUtil.getLong(actionRequest, "docId");
			String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
			
			EntDoc docData = EntDocLocalServiceUtil.getEntDoc(docId);
			docData.setDoc_deadline(doc_deadline);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			_log.info("New deadline is " + doc_deadline);
			_log.info("Inserting data to DB");
			
			docData = EntDocLocalServiceUtil.updateEntDoc(docData);
			_log.info("COMPLETED: Updated document: " + docId);
			SessionMessages.add(actionRequest, "request_processed", "Updated.");
			
		} catch (Exception e){
			_log.error("Fail to update deadline");
			e.printStackTrace();
		}
	}
	
public void doShowKey(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		
		_log.info("###################################################");
		_log.info("#         Display Public Key Function log         #");
		_log.info("###################################################");
		_log.info("START: Display Public Key Function");
		
			
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		long userId = currentUser.getUserId();

		try { 
			
			_log.info("Retrieving signer key from database");
			EntKey genkey = EntKeyLocalServiceUtil.getEntKey(userId);
			String sign_name = genkey.getSign_name();
			String pubKey = genkey.getPublickey_Data();
			
			if (pubKey != null){
				actionRequest.setAttribute("pubKey", pubKey);
				_log.info("COMPLETED: Found signer key: " + sign_name);
				SessionMessages.add(actionRequest, "request_processed", "Found signer key! ("+ sign_name +") Please proceed to verify the signature.");
			} else {
				_log.error("System unable to display public key to UI");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
				
			} 
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			String noKey = "No key available. Signer has not generate the key.";
			SessionErrors.add(actionRequest, "error-key-nokey");
			actionRequest.setAttribute("pubKey", noKey);
			_log.error("No key found for User ID: " + userId);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

		}
		
	}
	
	/**
	 * Function to fetch blob data of the uploaded document
	 */
	
    @Override
    public void serveResource(ResourceRequest resourceRequest,
            ResourceResponse resourceResponse) throws IOException,
            PortletException {
    	
        try {
            long fileId = ParamUtil.getLong(resourceRequest, "fileId");
            
            EntFileUpload fileup = EntFileUploadLocalServiceUtil.getEntFileUpload(fileId);

            if (fileup != null) {
            	
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