package uploadDoc.portlet;

import uploadDoc.constants.UploadDocPortletKeys;
import com._42Penguins.gosign.model.EntDoc;
import com._42Penguins.gosign.model.EntFileUpload;
import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil;
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
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
 * @author Raziman Dom
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=uploadDoc Portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UploadDocPortletKeys.UploadDoc, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class UploadDocPortlet extends MVCPortlet { 

	/**
	 * doView method to get information of current logged user
	 */

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		String currentCompURL = PortletURLUtil.getCurrent(renderRequest, renderResponse).toString();
		renderRequest.setAttribute("currentCompURL", currentCompURL);
		
		super.doView(renderRequest, renderResponse);
	}
	

    /*
	
	/**
	 * Auto complete form
	 *
	
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
        //ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        
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
    */

    /**
     * addDoc method to retrieve data from the form then set the data to db
     * @param actionRequest
     * @param actionResponse
     * @throws IOException
     * @throws PortletException
     * @throws NoSuchAlgorithmException
     */

	@ProcessAction(name = "addDoc")
	public void addDoc(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, NoSuchAlgorithmException {
		
		/*
		 * Fetch current date and time
		 */
		
		ZoneId zoneIdMYS = ZoneId.of("Asia/Kuala_Lumpur");
		LocalDateTime localDateTime = LocalDateTime.now(zoneIdMYS);
		DateTimeFormatter formatterTimeStamp = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss-A-N");
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		/*
		 * Fetch current user data
		 */
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		
		String currentHomeURL = themeDisplay.getURLHome();		
		String currentTimeStamp = localDateTime.format(formatterTimeStamp);
		String currentDate = localDateTime.format(formatterDate);	
		String currentCompURL = ParamUtil.getString(actionRequest, "currentCompURL");
		
		/**
		 * Initialize and retrieve data from upload form
		 */
		
		long docId = CounterLocalServiceUtil.increment();
		long currentUserId = currentUser.getUserId();
		long fileId = CounterLocalServiceUtil.increment();
		String req_name = currentUser.getFullName();
		String req_email = currentUser.getEmailAddress();
		String req_dateCreated = currentDate;
		String req_timeCreated = currentTimeStamp;
		String sign_email = ParamUtil.getString(actionRequest, "sign_email");
		String doc_title = ParamUtil.getString(actionRequest, "doc_title");
		String doc_description = ParamUtil.getString(actionRequest, "doc_description");
		String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
		String doc_type = ParamUtil.getString(actionRequest, "doc_type");
		
		/**
		 * Generate MD5 based on docId and timestamp. Format: docId-currentTimeStamp
		 */
		
		String docIDString = Long.toString(docId);
		String docIdTimestamp = docIDString + "-" + currentTimeStamp;	
	    MessageDigest docIdTimestampMD5 = MessageDigest.getInstance("MD5");
	    docIdTimestampMD5.update(docIdTimestamp.getBytes(),0,docIdTimestamp.length());
	    String docIdMD5String = new BigInteger(1,docIdTimestampMD5.digest()).toString(16);

		/**
		 * Upload file
		 */
	    
		UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String uploadedFileName = uploadRequest.getFileName("file");
		File file = uploadRequest.getFile("file");
		InputStream inputStream = new FileInputStream(file);
		OutputBlob blobData = new OutputBlob(inputStream, file.length());

		/**
		 * Call doInsertDB action method
		 */

		doInsertDB(
				docId, fileId, doc_title, req_name, req_email, sign_email,
				req_dateCreated, req_timeCreated, doc_deadline, doc_type,
				docIdMD5String, doc_description, currentUserId,
				uploadedFileName, blobData, file, 
				actionRequest, actionResponse);

		/**
		 * Call doSendEmail action method
		 */
		
		doSendEmail(
				docId, doc_title, doc_type, req_name, req_email, sign_email, 
				doc_deadline, req_dateCreated, currentCompURL, currentHomeURL, 
				actionRequest, actionResponse);

		/*
		 * Success message
		 */
		
		SessionMessages.add(actionRequest, "request_processed", "Signature request sent!");
		
	}
	
	/**
	 * doInsertDB action method. Insert all data to database.
	 * @param docId
	 * @param fileId
	 * @param req_name
	 * @param req_email
	 * @param sign_email
	 * @param req_dateCreated
	 * @param doc_deadline
	 * @param doc_type
	 * @param docIdMD5String
	 * @param doc_description
	 * @param currentUserId
	 * @param uploadedFileName
	 * @param blobData
	 * @param file
	 * @param actionRequest
	 * @param actionResponse
	 */

	@ProcessAction(name = "doInsertDB")
	public void doInsertDB(
			long docId, long fileId, String doc_title, String req_name, String req_email, String sign_email,
			String req_dateCreated, String req_timeCreated, String doc_deadline, String doc_type, String docIdMD5String, String doc_description,
			long currentUserId, String uploadedFileName, OutputBlob blobData, File file,
			ActionRequest actionRequest, ActionResponse actionResponse){
		
		try {

			System.out.println("Adding data to database...");
			EntDoc doc = EntDocLocalServiceUtil.createEntDoc(docId);
			EntFileUpload fileup = EntFileUploadLocalServiceUtil.createEntFileUpload(fileId);
			doc.setDoc_title(doc_title);
			doc.setReq_name(req_name);
			doc.setReq_email(req_email);
			doc.setSign_name("[Auto-generated]");
			doc.setSign_email(sign_email);
			doc.setReq_dateCreated(req_dateCreated);
			doc.setReq_timeCreated(req_timeCreated);
			doc.setDoc_deadline(doc_deadline);
			doc.setDoc_status("Pending");
			doc.setDoc_type(doc_type);
			doc.setDoc_md5(docIdMD5String);
			doc.setDoc_description(doc_description);
			doc.setUserId(currentUserId);
			doc.setFileId(fileId);
			fileup.setFile_name(uploadedFileName);
			fileup.setFile_blob(blobData);
			fileup.setFile_type(MimeTypesUtil.getContentType(file));
			
			fileup = EntFileUploadLocalServiceUtil.addEntFileUpload(fileup);
			doc = EntDocLocalServiceUtil.addEntDoc(doc);

			System.out.println("Data added to database");
			System.out.println("======================== End ======================");

			// actionResponse.setRenderParameter("mvcPath", "/details.jsp");

		} catch (SystemException e) {
			System.out.println("Failed to insert data to database");
			e.printStackTrace();
			System.out.println("======================== End ======================");

		}
		
	}
	
	/**
	 * doSendEmail action method. Send confirmation email to signature requester and approver.
	 * @param docId
	 * @param doc_type
	 * @param req_name
	 * @param req_email
	 * @param sign_email
	 * @param doc_deadline
	 * @param req_dateCreated
	 * @param currentCompURL
	 * @param currentHomeURL
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	
	@ProcessAction(name = "doSendEmail")
	public void doSendEmail(
			long docId, String doc_title, String doc_type, String req_name, String req_email, String sign_email, 
			String doc_deadline, String req_dateCreated, String currentCompURL, String currentHomeURL, 
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		try {
			
			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;
			
			fromAddress = new InternetAddress("noreply@42penguins.com");
			toAddress = new InternetAddress(req_email);
			MailMessage mailMessage = new MailMessage();
			
			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("Signature Request Successfully Submitted.");
			
			//mailMessage.setBody(body);
			
			mailMessage.setBody(""
					
					+ "<font face=\"arial\" size=\"2\">"
					+ "<p>Dear " + req_name + ",</p> "
					+ "<p>Your signature request has been succesfully submitted. Please login to check the request status.</p>"
					+ "<p><strong>Request Details:</strong></p>"
					+ "<table style=\"font-family:arial;font-size:13px\">"
					+ "<tr><td>Request ID:</td><td> " + docId + "</td></tr>"
					+ "<tr><td>Request Title:</td><td> " + doc_title + "</td></tr>"
					+ "<tr><td>Request Type:</td><td> " + doc_type + "</td></tr>"
					+ "<tr><td>Created On:</td><td> " + req_dateCreated + "</td></tr>"
					+ "<tr><td>Deadline:</td><td> " + doc_deadline + "</td></tr>"
					+ "<tr><td>Link:</td><td><a href=\"" + currentCompURL + "\"> "+ "Click here to view uploaded document</a></td></tr>"
					+ "</table>" 
					+ "<br>"
					+ "<p>Sincerely,<br>"
					+ "GoSign Team<br>" 
					+ "<a href=\"" + currentHomeURL + "\"> " + currentHomeURL
					+ "</a></p>" + "</font>");
			
			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			System.out.println("Email has been sent to requestor!");
			
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		
		try {
			
			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;
			
			fromAddress = new InternetAddress("noreply@42penguins.com");
			toAddress = new InternetAddress(sign_email);
			MailMessage mailMessage = new MailMessage();
			
			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("New Request for Signature");
			mailMessage.setBody("" 
					
					+ "<font face=\"arial\" size=\"2\">"
					+ "<p>Dear Signer, </p> "
					+ "<p>You have received a request from " + req_name +" to sign a document.  "
					+ "<a href=\"" + currentHomeURL +"\">Click here</a>"
					+ " to login to view and sign the document before " + doc_deadline + ".</p>"
					+ "<p><strong>Request Details:</strong></p>"
					+ "<table style=\"font-family:arial;font-size:13px\">"
					+ "<tr><td>Request ID:</td><td> " + docId + "</td></tr>"
					+ "<tr><td>Request Title:</td><td> " + doc_title + "</td></tr>"
					+ "<tr><td>Request Type:</td><td> " + doc_type + "</td></tr>"
					+ "<tr><td>Created On:</td><td> " + req_dateCreated + "</td></tr>"
					+ "<tr><td>Deadline:</td><td> " + doc_deadline + "</td></tr>"
					+ "</table>" 
					+ "<br>"
					+ "<p>Sincerely,<br>"
					+ "GoSign Team<br>" 
					+ "<a href=\"" + currentHomeURL + "\"> " + currentHomeURL
					+ "</a></p>" + "</font>");

			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			System.out.println("Email has been sent to signer!");

	} catch (AddressException e) {
		e.printStackTrace();
	}
	}
	
	/**
	 * doUpdateDoc action method. Retrieve latest deadline data from user then insert in DB
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	
	/*

	@ProcessAction(name = "doUpdateDoc")
	public void doUpdateDoc(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		System.out.println("======================== Start ======================");
		System.out.println("Retrieve data from update form...");
		System.out.println("...");
		System.out.println("...");

		long docId = ParamUtil.getLong(actionRequest, "docId");
		String doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline");
		try {

			System.out.println("Adding data to database...");

			EntDoc doc = EntDocLocalServiceUtil.createEntDoc(docId);
			doc.setDoc_deadline(doc_deadline);
			
			doc = EntDocLocalServiceUtil.updateEntDoc(doc);

			System.out.println("Data added to database");
			System.out.println("======================== End ======================");

			// actionResponse.setRenderParameter("mvcPath", "/details.jsp");

		} catch (SystemException e) {
			System.out.println("Failed to insert data to database");
			e.printStackTrace();
			System.out.println("======================== End ======================");

		}

	}*/
	

}