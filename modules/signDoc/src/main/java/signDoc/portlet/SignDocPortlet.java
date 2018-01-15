package signDoc.portlet;

import signDoc.constants.SignDocPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import DocRegistration.model.Document;
import DocRegistration.service.DocumentLocalServiceUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		//System.out.println(currentUser);
		
		String currentHomeURL = themeDisplay.getURLHome();
		System.out.println("currenthomeurl: " +currentHomeURL);
		
		//String currentCompURL = PortletURLUtil.getCurrent(renderRequest, renderResponse).toString();
		//System.out.println(currentCompURL);

		//LocalDateTime localDateTime = LocalDateTime.now();
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		//String formatDateTime = localDateTime.format(formatter);

		//renderRequest.setAttribute("currentUserId", currentUser.getUserId());
		//renderRequest.setAttribute("currentFirstName", currentUser.getFirstName());
		renderRequest.setAttribute("currentEmail", currentUser.getEmailAddress());
		//renderRequest.setAttribute("currentDateTime", formatDateTime);
		//renderRequest.setAttribute("currentCompURL", currentCompURL);
		//renderRequest.setAttribute("currentHomeURL", currentHomeURL);
		
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
			
			//String currentCompURL = ParamUtil.getString(actionRequest, "currentCompURL");
			//String currentHomeURL = ParamUtil.getString(actionRequest, "currentHomeURL");
			
			System.out.println("Signing document...");
			long docId = ParamUtil.getLong(actionRequest, "docId");
			String req_name = ParamUtil.getString(actionRequest, "req_name");
			String req_email = ParamUtil.getString(actionRequest, "req_email");
			String req_dateCreated = ParamUtil.getString(actionRequest, "req_dateCreated");
			//String req_dateModified = ParamUtil.getString(actionRequest, "req_dateModified");
			String sign_email = ParamUtil.getString(actionRequest, "sign_email");
			String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
			String doc_type = ParamUtil.getString(actionRequest, "doc_type");
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
			
			/*
			 * Function to send email for requestor
			 */
			
				InternetAddress fromAddress = null;
				InternetAddress toAddress = null;
				
				String req_emailMail = ParamUtil.getString(actionRequest, "req_email");
				
				fromAddress = new InternetAddress("noreply@42penguins.com");
				toAddress = new InternetAddress(req_emailMail);
				MailMessage mailMessage = new MailMessage();
				
				mailMessage.setTo(toAddress);
				mailMessage.setFrom(fromAddress);
				mailMessage.setSubject("GoSign Signature Status - Approved");
				
				//mailMessage.setBody(body);
				
				mailMessage.setBody(""
						+ "<font face=\"verdana\" size=\"2\">"
						+ "<p>Dear "+ req_name +",</p> " 
						+ "<p>Your signature request has been approved. Please login to verify the signature.</p>"
						+ "<p><strong>Request Details:</strong></p>"
						+ "<ul>"
						+ "<li>Request ID: " + docId + "</li>"
						+ "<li>Request Type: " + doc_type + "</li>"
						+ "<li>Created On: " + req_dateCreated + "</li>"				
						+ "<li>Deadline: " + doc_deadline + "</li>"
						+ "<li>Signed By: " + sign_email + "</li>"
						+ "</ul>"
						+ "<p>Sincerely,<br>GoSign Team<br></p>"
						+ "</font>"
						);
				
				mailMessage.setHTMLFormat(true);
				MailServiceUtil.sendEmail(mailMessage);
				System.out.println("Email has been sent to requestor!");
			
		} catch (Exception e){
			System.out.println("Fail to sign document...");
			e.printStackTrace();
		}
		System.out.println("================End doSignDoc=================");
}
	/*
	 * Reject Signature Module
	 */

	public void doRejectDoc(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws IOException, PortletException {
		
		System.out.println("================Start doSignDoc=================");
		try{
			
			//String currentCompURL = ParamUtil.getString(actionRequest, "currentCompURL");
			//String currentHomeURL = ParamUtil.getString(actionRequest, "currentHomeURL");
			
			System.out.println("Rejecting document...");
			long docId = ParamUtil.getLong(actionRequest, "docId");
			String req_name = ParamUtil.getString(actionRequest, "req_name");
			String req_email = ParamUtil.getString(actionRequest, "req_email");
			String req_dateCreated = ParamUtil.getString(actionRequest, "req_dateCreated");
			//String req_dateModified = ParamUtil.getString(actionRequest, "req_dateModified");
			String sign_email = ParamUtil.getString(actionRequest, "sign_email");
			String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
			String doc_type = ParamUtil.getString(actionRequest, "doc_type");
			String doc_status = "Rejected";
			
			
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
			
			/*
			 * Function to send email for requestor
			 */
			
				InternetAddress fromAddress = null;
				InternetAddress toAddress = null;
				
				String req_emailMail = ParamUtil.getString(actionRequest, "req_email");
				
				fromAddress = new InternetAddress("noreply@42penguins.com");
				toAddress = new InternetAddress(req_emailMail);
				MailMessage mailMessage = new MailMessage();
				
				mailMessage.setTo(toAddress);
				mailMessage.setFrom(fromAddress);
				mailMessage.setSubject("GoSign Signature Status - Disapproved");
				
				//mailMessage.setBody(body);
				
				mailMessage.setBody(""
						+ "<font face=\"verdana\" size=\"2\">"
						+ "<p>Dear "+ req_name +",</p> " 
						+ "<p>Your signature request has been disapproved. "
						+ "If this request need more justification, please login and update your request.</p>"
						+ "<p><strong>Request Details:</strong></p>"
						+ "<ul>"
						+ "<li>Request ID: " + docId + "</li>"
						+ "<li>Request Type: " + doc_type + "</li>"
						+ "<li>Created On: " + req_dateCreated + "</li>"				
						+ "<li>Deadline: " + doc_deadline + "</li>"
						+ "<li>Rejected By: " + sign_email + "</li>"
						+ "</ul>"
						+ "<p>Sincerely,<br>GoSign Team<br></p>"
						+ "</font>"
						);
				
				mailMessage.setHTMLFormat(true);
				MailServiceUtil.sendEmail(mailMessage);
				System.out.println("Email has been sent to requestor!");
			
		} catch (Exception e){
			System.out.println("Fail to reject document...");
			e.printStackTrace();
		}
		System.out.println("================End doSignDoc=================");
}

	
	/*
	 * Justification Signature Module
	 */
	
	public void doReqJustification(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws IOException, PortletException {
		
		System.out.println("================Start doSignDoc=================");
		try{
			
			//String currentCompURL = ParamUtil.getString(actionRequest, "currentCompURL");
			//String currentHomeURL = ParamUtil.getString(actionRequest, "currentHomeURL");
			
			System.out.println("Requesting justification...");
			long docId = ParamUtil.getLong(actionRequest, "docId");
			String req_name = ParamUtil.getString(actionRequest, "req_name");
			//String req_email = ParamUtil.getString(actionRequest, "req_email");
			String req_dateCreated = ParamUtil.getString(actionRequest, "req_dateCreated");
			//String req_dateModified = ParamUtil.getString(actionRequest, "req_dateModified");
			String sign_email = ParamUtil.getString(actionRequest, "sign_email");
			String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
			String doc_type = ParamUtil.getString(actionRequest, "doc_type");
			String justificationMsg = ParamUtil.getString(actionRequest, "justificationMsg");
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
			
			/*
			 * Function to send email for requestor
			 */
			
				InternetAddress fromAddress = null;
				InternetAddress toAddress = null;
				
				String req_emailMail = ParamUtil.getString(actionRequest, "req_email");
				
				fromAddress = new InternetAddress("noreply@42penguins.com");
				toAddress = new InternetAddress(req_emailMail);
				MailMessage mailMessage = new MailMessage();
				
				mailMessage.setTo(toAddress);
				mailMessage.setFrom(fromAddress);
				mailMessage.setSubject("GoSign Signature Status - Pending Justification");
				
				//mailMessage.setBody(body);
				
				mailMessage.setBody(""
						+ "<font face=\"verdana\" size=\"2\">"
						+ "<p>Dear "+ req_name +",</p> " 
						+ "<p>Please provide more justification on this signature request. "
						+ "Below is request details and message from the signer.</p>"
						+ "<p><strong>Request Details:</strong></p>"
						+ "<ul>"
						+ "<li>Request ID: " + docId + "</li>"
						+ "<li>Request Type: " + doc_type + "</li>"
						+ "<li>Created On: " + req_dateCreated + "</li>"				
						+ "<li>Deadline: " + doc_deadline + "</li>"
						+ "<li>Signer: " + sign_email + "</li>"
						+ "<li>Signer Comments: " + justificationMsg + "</li>"
						+ "</ul>"
						+ "<p>Sincerely,<br>GoSign Team<br></p>"
						+ "</font>"
						);
				
				mailMessage.setHTMLFormat(true);
				MailServiceUtil.sendEmail(mailMessage);
				System.out.println("Email has been sent to requestor!");
			
		} catch (Exception e){
			System.out.println("Fail to sign document...");
			e.printStackTrace();
		}
		System.out.println("================End doSignDoc=================");
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
	
}