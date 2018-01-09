package signDoc.portlet;

import signDoc.constants.SignDocPortletKeys;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import DocRegistration.model.Document;
import DocRegistration.service.DocumentLocalServiceUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author razim
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=signDoc Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + SignDocPortletKeys.SignDoc,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SignDocPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();

		renderRequest.setAttribute("currentFirstName", currentUser.getFirstName());
		renderRequest.setAttribute("currentEmail", currentUser.getEmailAddress());
		
		super.doView(renderRequest, renderResponse);
	}
	
	public void doBack(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws IOException, PortletException {
		
		System.out.println("Go back to view page.");
		actionResponse.setRenderParameter("mvcPath", "/view.jsp");
		
	}
	
	public void doSignDoc(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws IOException, PortletException {
		
		System.out.println("================Start doSignDoc=================");
		try{
			System.out.println("Signing document...");
			Long docId = ParamUtil.getLong(actionRequest, "docId");
			String doc_status = "Signed";
			
			Document doc = DocumentLocalServiceUtil.getDocument(docId);
			doc.setDoc_status(doc_status);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			System.out.println("Status updated: " + doc_status);
			System.out.println("Inserting data to DB");
			
			doc=DocumentLocalServiceUtil.updateDocument(doc);
			
			if (!addProcessActionSuccessMessage) {
			return;
			}
			
			String successMessage = ParamUtil.getString(actionRequest, "successMessage");
			SessionMessages.add(actionRequest, "request_processed", successMessage);
			
		} catch (Exception e){
			System.out.println("Fail to sign document...");
			e.printStackTrace();
		}
		System.out.println("================End doSignDoc=================");
		
	}
	
	public void doRejectDoc(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws IOException, PortletException {
		
		System.out.println("================Start doRejectDoc=================");
		try{
			System.out.println("Rejecting document...");
			Long docId = ParamUtil.getLong(actionRequest, "docId");
			String doc_status = "Rejected";
			
			Document doc = DocumentLocalServiceUtil.getDocument(docId);
			doc.setDoc_status(doc_status);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			System.out.println("Status updated: " + doc_status);
			System.out.println("Inserting data to DB");
			
			doc=DocumentLocalServiceUtil.updateDocument(doc);
			
		} catch (Exception e){
			System.out.println("Fail to reject document...");
			e.printStackTrace();
		}
		System.out.println("================End doRejectDoc=================");
		
	}
	
	public void doReqJustification(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws IOException, PortletException {
		
		System.out.println("============Start doReqJustification==========");
		try{
			System.out.println("Requesting justification...");
			Long docId = ParamUtil.getLong(actionRequest, "docId");
			String doc_status = "Need Justification";
			
			Document doc = DocumentLocalServiceUtil.getDocument(docId);
			doc.setDoc_status(doc_status);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			System.out.println("Status updated: " + doc_status);
			System.out.println("Inserting data to DB");
			
			doc=DocumentLocalServiceUtil.updateDocument(doc);
			
		} catch (Exception e){
			System.out.println("Fail to request justification...");
			e.printStackTrace();
		}
		System.out.println("============End doReqJustification==========");
		
	}
	
}