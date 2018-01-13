package uploadDoc.portlet;

//import java.io.File;
//import org.apache.commons.io.FileUtils;
import uploadDoc.constants.UploadDocPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
//import com.liferay.content.util.ContentUtil;

import DocRegistration.model.Document;
import DocRegistration.service.DocumentLocalServiceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

//import javax.mail.Message;
//import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author razim
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=uploadDoc Portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UploadDocPortletKeys.UploadDoc, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class UploadDocPortlet extends MVCPortlet {

	public void uploadFileAction(ActionRequest actionRequest, ActionResponse actionResponse) {

	}

	/*
	 * doView method to get information of current logged user
	 * 
	 */

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();

		/*
		 * Function to fetch current date and time
		 *
		 */

		// LocalDate localDate = LocalDate.now();
		// LocalTime localTime = LocalTime.now();
		// Month month = localDateTime.getMonth();
		// int day = localDateTime.getDayOfMonth();
		// int seconds = localDateTime.getSecond();
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy
		// HH:mm:ss");

		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formatDateTime = localDateTime.format(formatter);

		renderRequest.setAttribute("currentUserId", currentUser.getUserId());
		renderRequest.setAttribute("currentFirstName", currentUser.getFirstName());
		renderRequest.setAttribute("currentEmail", currentUser.getEmailAddress());
		renderRequest.setAttribute("currentDateTime", formatDateTime);

		super.doView(renderRequest, renderResponse);
	}
	
	/*
	 * Function to for email autocomplete form
	 */
	
    @Override
    public void serveResource(ResourceRequest resourceRequest,
            ResourceResponse resourceResponse) throws IOException, PortletException {
            String cmd = ParamUtil.getString(resourceRequest, Constants.CMD);
            System.out.println("Constants.CMD: " + cmd);
            if (cmd.equals("get_users")) {
                getUsers(resourceRequest, resourceResponse);
            }
        }
	
    public void getUsers(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
        JSONArray usersJSONArray = JSONFactoryUtil.createJSONArray();
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        
        System.out.println("=====Retrieving value from text field=====");
        String userEmail = ParamUtil.getString(resourceRequest, "userEmail");
        System.out.println("User entered keyword: ===> " + userEmail);
        
        DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class, PortalClassLoaderUtil.getClassLoader());
        Criterion criterion = RestrictionsFactoryUtil.like("emailAddress",  StringPool.PERCENT + userEmail + StringPool.PERCENT);
        userQuery.add(criterion);
        JSONObject userJSON = null;
        System.out.println("User query string: ===> " + userQuery.toString());
        
        try {
            List < User > userList = UserLocalServiceUtil.dynamicQuery(userQuery);
            System.out.println("Found word in DB? 0:NO 1:YES ===> " + userList.size());
            for (User user: userList) {
                userJSON = JSONFactoryUtil.createJSONObject();
                userJSON.put("signId", user.getUserId());
                userJSON.put("email", user.getEmailAddress());
                userJSON.put("firstName", user.getFirstName());
                usersJSONArray.put(userJSON);
                System.out.println("Fetch data from signer email: ");
                System.out.println(userJSON);
                
            }
        } catch (Exception e) {}
        PrintWriter out = resourceResponse.getWriter();
        out.println(usersJSONArray.toString());
    }
    
    

	/*
	 * addDoc method to retrieve data from the form then set the data to db
	 * 
	 */

	@ProcessAction(name = "addDoc")
	public void addDoc(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		System.out.println("====================== Start ======================");
		System.out.println("Retrieve data from upload form...");
		System.out.println("...");
		System.out.println("...");

		long docId = CounterLocalServiceUtil.increment();
		long currentUserId = ParamUtil.getLong(actionRequest, "currentUserId");
		long signId = ParamUtil.getLong(actionRequest, "signId");
		long fileId = CounterLocalServiceUtil.increment();
		String req_name = ParamUtil.getString(actionRequest, "req_name");
		String req_email = ParamUtil.getString(actionRequest, "req_email");
		String req_dateCreated = ParamUtil.getString(actionRequest, "req_dateCreated");
		String sign_email = ParamUtil.getString(actionRequest, "sign_email");
		String doc_description = ParamUtil.getString(actionRequest, "doc_description");
		String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
		String doc_type = ParamUtil.getString(actionRequest, "doc_type");

		System.out.println("Retrieved data:");
		System.out.println("Document ID: " + docId);
		System.out.println("Requestor ID: " + currentUserId);
		System.out.println("Signer ID: " + signId);
		System.out.println("File ID: " + fileId);
		System.out.println("Requestor Name: " + req_name);
		System.out.println("Requestor Email: " + req_email);
		System.out.println("Request Created: " + req_dateCreated);
		System.out.println("Signer Email: " + sign_email);
		System.out.println("Document Description/Justification: " + doc_description);
		System.out.println("Deadline: " + doc_deadline);
		System.out.println("Document Type: " + doc_type);
		System.out.println("...");
		System.out.println("...");
		

		/*
		 * Function to upload
		 * 
		 */

		/**
		 * Create and set the primary key
		 */
		// long id =
		// CounterLocalServiceUtil.increment(BlobDemo.class.getName());
		// BlobDemo blobDemo = BlobDemoLocalServiceUtil.createBlobDemo(id);

		// blobDemo.setName(uploadedFileName);
		// blobDemo.setBlobData(blobData);
		// blobDemo.setMimeType(MimeTypesUtil.getContentType(file));
		// BlobDemoLocalServiceUtil.addBlobDemo(blobDemo);

		/*
		 * Function to add data to database
		 */

		try {

			UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

			/**
			 * Get the uploaded file name with extension
			 */

			String uploadedFileName = uploadRequest.getFileName("file");

			File file = uploadRequest.getFile("file");

			InputStream inputStream = new FileInputStream(file);
			/**
			 * Below is the actual blob data
			 */
			OutputBlob blobData = new OutputBlob(inputStream, file.length());

			System.out.println("Adding data to database...");

			Document doc = DocumentLocalServiceUtil.createDocument(docId);
			doc.setReq_name(req_name);
			doc.setReq_email(req_email);
			//doc.setSignId(signId);
			doc.setSign_email(sign_email);
			doc.setReq_dateCreated(req_dateCreated);
			doc.setDoc_deadline(doc_deadline);
			doc.setDoc_description(doc_description);
			doc.setDoc_status("Pending");
			doc.setDoc_type(doc_type);
			doc.setUserId(currentUserId);
			doc.setFileId(fileId);
			doc.setFile_name(uploadedFileName);
			doc.setFile_blob(blobData);
			doc.setFile_type(MimeTypesUtil.getContentType(file));

			doc = DocumentLocalServiceUtil.addDocument(doc);

			System.out.println("Data added to database");
			System.out.println("======================== End ======================");

			// actionResponse.setRenderParameter("mvcPath", "/details.jsp");

		} catch (SystemException e) {
			System.out.println("Failed to insert data to database");
			e.printStackTrace();
			System.out.println("======================== End ======================");

		}
		
		/*
		 * Function to send email for requestor
		 */
	
		try {
			
			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;
			
			String req_emailMail = ParamUtil.getString(actionRequest, "req_email");
			
			fromAddress = new InternetAddress("noreply@42penguins.com");
			toAddress = new InternetAddress(req_emailMail);
			MailMessage mailMessage = new MailMessage();
			
			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("Signature Request Successfully Submitted.");
			mailMessage.setBody(""
					+ "<p>Dear "+ req_name +",</p> " 
					+ "<p>You request has been succesfully submitted.</p>"
					+ "<p><strong>Request Details:</strong></p>"
					+ "<ul>"
					+ "<li>Requested Title: " + req_name + "</li>"
					+ "<li>Requested By: " + req_name + "</li>"
					+ "<li>Document Name: " + req_name + "</li>"
					+ "<li>Request Type: " + doc_type + "</li>"
					+ "<li>Deadline: " + doc_deadline + "</li>"
					+ "</ul>"
					+ "<p>&nbsp;</p>"
					+ "<p>GoSign Team</p>"
					);
			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			System.out.println("Email has been sent to requestor!");
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		/*
		 * Function to send email for signer
		 */
	
		try {
			
			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;
			
			String sign_emailMail = ParamUtil.getString(actionRequest, "sign_email");
			
			fromAddress = new InternetAddress("noreply@42penguins.com");
			toAddress = new InternetAddress(sign_emailMail);
			MailMessage mailMessage = new MailMessage();
			
			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("New Request for Signature");
			mailMessage.setBody("" 
					+ "<p>You have received a request from " + req_name +" to sign a document.&nbsp;Please login to view and sign the document before&nbsp;</p>"
					+ "<p><strong>Request Details:</strong></p>"
					+ "<ul>"
					+ "<li>Requested Title: " + req_name + "</li>"
					+ "<li>Requested By: " + req_name + "</li>"
					+ "<li>Document Name: " + req_name + "</li>"
					+ "<li>Request Type: " + doc_type + "</li>"
					+ "<li>Deadline: " + doc_deadline + "</li>"
					+ "</ul>"
					+ "<p>&nbsp;</p>"
					+ "<p>GoSign Team</p>"
					);
			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			System.out.println("Email has been sent to signer!");
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		/*
		 * Another email function
		 
		
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		
		String body = ContentUtil.get(null, "/mail/reqsign.tmpl", true);
		body = StringUtil.replace(body, new String[] { "[$NAME$]","[$RESULT$]","[$PERCENTAGE$]","[$EXAM$]" }, new String[] { "Ravi", "CONGRATULATION" ,"80%" , "CCLP"});
		try {
			fromAddress = new InternetAddress("aa665845@gmail.com");
			toAddress = new InternetAddress("adit2787@gmail.com");
			MailMessage mailMessage = new MailMessage();
			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("Send mail by Using Tempelate");
			mailMessage.setBody(body);
			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			System.out.println("Send mail by Using Tempelate");
		} catch (AddressException e) {
			e.printStackTrace();
		}
		*/
	}

	@ProcessAction(name = "updateDoc")
	public void updateDoc(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		System.out.println("======================== Start ======================");
		System.out.println("Retrieve data from update form...");
		System.out.println("...");
		System.out.println("...");

		long docId = CounterLocalServiceUtil.increment();
		String req_name = ParamUtil.getString(actionRequest, "req_name");
		String req_email = ParamUtil.getString(actionRequest, "req_email");
		String sign_email = ParamUtil.getString(actionRequest, "sign_email");
		String doc_description = ParamUtil.getString(actionRequest, "doc_description");
		String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
		String doc_type = ParamUtil.getString(actionRequest, "doc_type");

		System.out.println("Retrieved data:");
		System.out.println("Document ID: " + docId);
		System.out.println("Requestor Name: " + req_name);
		System.out.println("Requestor Email: " + req_email);
		System.out.println("Signer Email: " + sign_email);
		System.out.println("Document Description/Justification: " + doc_description);
		System.out.println("Deadline: " + doc_deadline);
		System.out.println("Document Type: " + doc_type);
		System.out.println("...");
		System.out.println("...");

		try {

			System.out.println("Adding data to database...");

			Document doc = DocumentLocalServiceUtil.createDocument(docId);
			doc.setReq_name(req_name);
			doc.setReq_email(req_email);
			doc.setSign_email(sign_email);
			doc.setDoc_deadline(doc_deadline);
			doc.setDoc_description(doc_description);
			doc.setDoc_status("Unsigned");
			doc.setDoc_type(doc_type);

			doc = DocumentLocalServiceUtil.addDocument(doc);

			System.out.println("Data added to database");
			System.out.println("======================== End ======================");

			// actionResponse.setRenderParameter("mvcPath", "/details.jsp");

		} catch (SystemException e) {
			System.out.println("Failed to insert data to database");
			e.printStackTrace();
			System.out.println("======================== End ======================");

		}

	}
	
	

	public void sendMailMessage (ActionRequest actionRequest, ActionResponse actionResponse)
				throws IOException, PortletException {
		
		//InternetAddress recipient = new InternetAddress()
		
		//InternetAddress toAddress = InternetAddress.toString(addresses)
        //InternetAddress fromAddress = InternetAddress.getLocalAddress(session);
		
		/*
		String to = "razimandom@gmail.com";//change accordingly  
	      String from = "razimandom@gmail.com";//change accordingly  
	      String host = "localhost";//or IP address  
	  
	     //Get the session object  
	      Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", host);  
	      Session session = Session.getDefaultInstance(properties);  
	  
	     //compose the message  
	      try{  
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(from));  
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	         message.setSubject("Ping");  
	         message.setText("Hello, this is example of sending email  ");  
	  
	         // Send message  
	         Transport.send(message);  
	         System.out.println("message sent successfully....");  
	  
	      }catch (MessagingException mex) {mex.printStackTrace();}  
	   }  
					*/
		
		/*
					System.out.println("====sendMailMessage===");
					//String mailSubject=ParamUtil.getString(actionRequest,"mailSubject");
					//String mailBody=ParamUtil.getString(actionRequest,"editor");
					//String senderMailAddress=ParamUtil.getString(actionRequest,"senderEmailAddess");
					//String receiverMailAddress=ParamUtil.getString(actionRequest,"receiverEmailAddess");
					String mailSubject="mailSubject";
					String mailBody="mailbody";
					String senderMailAddress="razimandom@gmail.com";
					String receiverMailAddress="razimandom@gmail.com";
					
					System.out.println("1111"+mailBody);
		try {
		            MailMessage mailMessage=new MailMessage();
					mailMessage.setBody(mailBody);
					mailMessage.setSubject(mailSubject);
					mailMessage.setFrom(new InternetAddress(senderMailAddress));
					mailMessage.setTo(new InternetAddress(receiverMailAddress));
					MailServiceUtil.sendEmail(mailMessage);
					//SessionMessages.add((HttpServletRequest) actionRequest.getPortletSession(),"mail-send-success");
		}
	catch (Exception e) {
	    e.printStackTrace();
	}*/
	}

}