package viewDoc.portlet;

import viewDoc.constants.ViewDocPortletKeys;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import DocRegistration.model.Document;
import DocRegistration.service.DocumentLocalServiceUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author razim
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
	
	/*
	 * Delete document method
	 */
	
	public void delDocument(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		long docId = ParamUtil.getLong(actionRequest, "docId");
		try {
			DocumentLocalServiceUtil.deleteDocument(docId);
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
	
	public void updateDoc(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		
		System.out.println("================Start updateDoc=================");
		try{
			System.out.println("Updating deadline...");
			Long docId = ParamUtil.getLong(actionRequest, "docId");
			String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
			
			Document doc = DocumentLocalServiceUtil.getDocument(docId);
			doc.setDoc_deadline(doc_deadline);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			System.out.println("New deadline is " + doc_deadline);
			System.out.println("Inserting data to DB");
			
			doc=DocumentLocalServiceUtil.updateDocument(doc);
			
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
            long dataId = ParamUtil.getLong(resourceRequest, "dataId");
            
            Document doc = DocumentLocalServiceUtil.getDocument(dataId);

            if (doc != null) {
                Blob blob = doc.getFile_blob();
                byte[] binaryData = blob.getBytes(1, (int) blob.length());
                // resourceResponse.setContentType(blobDemo.getMimeType());
                resourceResponse.setContentType("application/application-download");
                resourceResponse.setProperty("Content-disposition","attachement; filename=" + doc.getFile_name());
                OutputStream o = resourceResponse.getPortletOutputStream();
                o.write(binaryData);
                o.flush();
                o.close();
                resourceResponse.flushBuffer();
            }
 
        } catch (Exception e) {
 
        }
    }
	
	public void doView(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {	
	}
	
	public void docSearchContainer(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
	
		
	}
	
	
	
}