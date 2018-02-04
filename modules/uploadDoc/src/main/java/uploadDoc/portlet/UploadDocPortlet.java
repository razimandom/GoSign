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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
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

	private static Log _log = LogFactoryUtil.getLog(UploadDocPortlet.class);

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
	 * 
	 * /** Auto complete form
	 *
	 * 
	 * @Override public void serveResource(ResourceRequest resourceRequest,
	 * ResourceResponse resourceResponse) throws IOException, PortletException {
	 * String cmd = ParamUtil.getString(resourceRequest, Constants.CMD);
	 * System.out.println("Constants.CMD: " + cmd); if (cmd.equals("get_users"))
	 * { getUsers(resourceRequest, resourceResponse); } }
	 * 
	 * 
	 * public void getUsers(ResourceRequest resourceRequest, ResourceResponse
	 * resourceResponse) throws IOException, PortletException { JSONArray
	 * usersJSONArray = JSONFactoryUtil.createJSONArray(); //ThemeDisplay
	 * themeDisplay = (ThemeDisplay)
	 * resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
	 * 
	 * System.out.println("=====Retrieving value from text field====="); String
	 * userEmail = ParamUtil.getString(resourceRequest, "userEmail");
	 * System.out.println("User entered keyword: ===> " + userEmail);
	 * 
	 * DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class,
	 * PortalClassLoaderUtil.getClassLoader()); Criterion criterion =
	 * RestrictionsFactoryUtil.like("emailAddress", StringPool.PERCENT +
	 * userEmail + StringPool.PERCENT); userQuery.add(criterion); JSONObject
	 * userJSON = null; System.out.println("User query string: ===> " +
	 * userQuery.toString());
	 * 
	 * try { List < User > userList =
	 * UserLocalServiceUtil.dynamicQuery(userQuery);
	 * System.out.println("Found word in DB? 0:NO 1:YES ===> " +
	 * userList.size()); for (User user: userList) { userJSON =
	 * JSONFactoryUtil.createJSONObject(); userJSON.put("signId",
	 * user.getUserId()); userJSON.put("email", user.getEmailAddress());
	 * userJSON.put("firstName", user.getFirstName());
	 * usersJSONArray.put(userJSON);
	 * System.out.println("Fetch data from signer email: ");
	 * System.out.println(userJSON);
	 * 
	 * } } catch (Exception e) {} PrintWriter out =
	 * resourceResponse.getWriter(); out.println(usersJSONArray.toString()); }
	 */

	/**
	 * addDoc method to retrieve data from the form then set the data to db
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws NoSuchAlgorithmException
	 */

	@ProcessAction(name = "addDoc")
	public void addDoc(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {

		_log.info("###################################################");
		_log.info("#                                                 #");
		_log.info("#              Upload Document log                #");
		_log.info("#                                                 #");
		_log.info("###################################################");
		_log.info("START: Upload Document Portlet");
		try {

			/*
			 * Fetch current date and time
			 */

			_log.info("Fetching current date");
			ZoneId zoneIdMYS = ZoneId.of("Asia/Kuala_Lumpur");
			LocalDateTime localDateTime = LocalDateTime.now(zoneIdMYS);
			DateTimeFormatter formatterTimeStamp = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss-A-N");
			DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			/*
			 * Fetch current user data
			 */

			_log.info("Fetching data of current logged user");
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User currentUser = themeDisplay.getUser();
			_log.info("Found user: " + currentUser.getUserId());

			String currentHomeURL = themeDisplay.getURLHome();
			String currentTimeStamp = localDateTime.format(formatterTimeStamp);
			String currentDate = localDateTime.format(formatterDate);
			String currentCompURL = ParamUtil.getString(actionRequest, "currentCompURL");

			/*
			 * Initialize and retrieve data from upload form
			 */

			_log.info("Fetching data from submitted form");
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
			_log.info("Start creating data for doc ID: " + docId);

			/*
			 * Generate MD5 based on docId and timestamp. Format:
			 * docId-currentTimeStamp
			 */

			_log.info("Generating MD5 file");
			String docIDString = Long.toString(docId);
			String docIdTimestamp = docIDString + "-" + currentTimeStamp;
			MessageDigest docIdTimestampMD5 = MessageDigest.getInstance("MD5");
			docIdTimestampMD5.update(docIdTimestamp.getBytes(), 0, docIdTimestamp.length());
			String docIdMD5String = new BigInteger(1, docIdTimestampMD5.digest()).toString(16);

			/*
			 * Fetch file data
			 */

			_log.info("Fetching data of uploaded file");
			UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
			String uploadedFileName = uploadRequest.getFileName("file");
			File file = uploadRequest.getFile("file");
			InputStream inputStream = new FileInputStream(file);
			OutputBlob blobData = new OutputBlob(inputStream, file.length());

			/*
			 * Retrieve file size
			 */

			_log.info("Retrieving size of the uploaded file");

			double KB = (1 * 1024);
			double MB = (1 * KB * 1000);
			double maxSizeMB = 10;
			double bytesMaxFileSize = maxSizeMB * MB;
			double bytesFile = file.length();
			double fileKB = bytesFile / KB;
			double fileMB = bytesFile / MB;

			DecimalFormat df = new DecimalFormat("#.##");
			String fileKBformat = df.format(fileKB);
			String fileMBformat = df.format(fileMB);

			_log.info("Uploaded file in KB: " + fileKBformat + "KB");
			_log.info("Uploaded file in MB: " + fileMBformat + "MB");

			/*
			 * Validation check
			 */

			/*
			 * Validation check if deadline date less than current date
			 */
			
			_log.info("Check if deadline date parameter less than current date");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Date localDate = new Date();
			String sdfLocalDate = sdf.format(localDate);
	
	        Date sdfDeadline = sdf.parse(doc_deadline);
	        Date sdfDateNow = sdf.parse(sdfLocalDate);
			
			if (sdfDeadline.after(sdfDateNow) || sdfDeadline.equals(sdfDateNow)) {

				/*
				 * Validation check if file size more than size limit
				 */

				_log.info("Check if uploaded file more than " + maxSizeMB + "MB");

				if (bytesFile < bytesMaxFileSize) {

					/*
					 * Call doInsertDB action method
					 */

					doInsertDB(docId, fileId, doc_title, req_name, req_email, sign_email, req_dateCreated,
							req_timeCreated, doc_deadline, doc_type, docIdMD5String, doc_description, currentUserId,
							uploadedFileName, blobData, file, currentCompURL, currentHomeURL, actionRequest,
							actionResponse);

				} else if (bytesFile > bytesMaxFileSize) {
					_log.warn("File size exceeded limit " + maxSizeMB + "MB");
					SessionErrors.add(actionRequest, "error-file-size");
				} else {
					_log.error("Unable to fetch file size");
				}

			} else if (sdfDeadline.before(sdfDateNow)) {

				_log.warn("Deadline date parameter less than or equal to current date");
				SessionErrors.add(actionRequest, "error-deadline");

			} else {

				_log.error("Error validating date");
			}

		} catch (Exception e) {
			_log.error("Signature request submission failed");
			SessionErrors.add(actionRequest, "error-submit");
			e.printStackTrace();
		}

	}

	/**
	 * doInsertDB action method. Insert all data to database.
	 * 
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
	public void doInsertDB(long docId, long fileId, String doc_title, String req_name, String req_email,
			String sign_email, String req_dateCreated, String req_timeCreated, String doc_deadline, String doc_type,
			String docIdMD5String, String doc_description, long currentUserId, String uploadedFileName,
			OutputBlob blobData, File file, String currentCompURL, String currentHomeURL, ActionRequest actionRequest,
			ActionResponse actionResponse) {

		try {
			_log.info("Inserting data to database");

			EntDoc doc = EntDocLocalServiceUtil.createEntDoc(docId);
			EntFileUpload fileup = EntFileUploadLocalServiceUtil.createEntFileUpload(fileId);
			doc.setDoc_title(doc_title);
			doc.setReq_name(req_name);
			doc.setReq_email(req_email);
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

			/*
			 * Call doSendEmail action method
			 */

			doSendEmail(docId, doc_title, doc_type, req_name, req_email, sign_email, doc_deadline, req_dateCreated,
					currentCompURL, currentHomeURL, actionRequest, actionResponse);

		} catch (SystemException e) {
			_log.error("Failed to insert data to database");
			SessionErrors.add(actionRequest, "error-submit");
			e.printStackTrace();

		} catch (IOException e) {
			SessionErrors.add(actionRequest, "error-submit");
			e.printStackTrace();
		} catch (PortletException e) {
			SessionErrors.add(actionRequest, "error-submit");
			e.printStackTrace();
		}

	}

	/**
	 * doSendEmail action method. Send confirmation email to signature requester
	 * and approver.
	 * 
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
	public void doSendEmail(long docId, String doc_title, String doc_type, String req_name, String req_email,
			String sign_email, String doc_deadline, String req_dateCreated, String currentCompURL,
			String currentHomeURL, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		try {

			/*
			 * Send email to requester
			 */

			_log.info("Sending email requester");

			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;

			fromAddress = new InternetAddress("noreply@42penguins.com");
			toAddress = new InternetAddress(req_email);
			MailMessage mailMessage = new MailMessage();

			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("Signature Request Successfully Submitted.");

			mailMessage.setBody(""

					+ "<font face=\"arial\" size=\"2\">" + "<p>Dear " + req_name + ",</p> "
					+ "<p>Your signature request has been succesfully submitted. Please login to check the request status.</p>"
					+ "<p><strong>Request Details:</strong></p>" + "<table style=\"font-family:arial;font-size:13px\">"
					+ "<tr><td>Request ID:</td><td> " + docId + "</td></tr>" + "<tr><td>Request Title:</td><td> "
					+ doc_title + "</td></tr>" + "<tr><td>Request Type:</td><td> " + doc_type + "</td></tr>"
					+ "<tr><td>Created On:</td><td> " + req_dateCreated + "</td></tr>" + "<tr><td>Deadline:</td><td> "
					+ doc_deadline + "</td></tr>" + "<tr><td>Link:</td><td><a href=\"" + currentCompURL + "\"> "
					+ "Click here to view uploaded document</a></td></tr>" + "</table>" + "<br>" + "<p>Sincerely,<br>"
					+ "GoSign Team<br>" + "<a href=\"" + currentHomeURL + "\"> " + currentHomeURL + "</a></p>"
					+ "</font>");

			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);

			/*
			 * Send email to signer
			 */

			_log.info("Sending email signer");
			InternetAddress fromAddressSigner = null;
			InternetAddress toAddressSigner = null;

			fromAddressSigner = new InternetAddress("noreply@42penguins.com");
			toAddressSigner = new InternetAddress(sign_email);
			MailMessage mailMessageSigner = new MailMessage();

			mailMessageSigner.setTo(toAddressSigner);
			mailMessageSigner.setFrom(fromAddressSigner);
			mailMessageSigner.setSubject("New Request for Signature");
			mailMessageSigner.setBody(""

					+ "<font face=\"arial\" size=\"2\">" + "<p>Dear Signer, </p> "
					+ "<p>You have received a request from " + req_name + " to sign a document.  " + "<a href=\""
					+ currentHomeURL + "\">Click here</a>" + " to login to view and sign the document before "
					+ doc_deadline + ".</p>" + "<p><strong>Request Details:</strong></p>"
					+ "<table style=\"font-family:arial;font-size:13px\">" + "<tr><td>Request ID:</td><td> " + docId
					+ "</td></tr>" + "<tr><td>Request Title:</td><td> " + doc_title + "</td></tr>"
					+ "<tr><td>Request Type:</td><td> " + doc_type + "</td></tr>" + "<tr><td>Created On:</td><td> "
					+ req_dateCreated + "</td></tr>" + "<tr><td>Deadline:</td><td> " + doc_deadline + "</td></tr>"
					+ "</table>" + "<br>" + "<p>Sincerely,<br>" + "GoSign Team<br>" + "<a href=\"" + currentHomeURL
					+ "\"> " + currentHomeURL + "</a></p>" + "</font>");

			mailMessageSigner.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessageSigner);

			/*
			 * Success message
			 */

			_log.info("COMPLETED: Signature request completed ID: " + docId);
			SessionMessages.add(actionRequest, "request_processed", "Signature request sent!");

		} catch (AddressException e) {
			_log.error("Failed send email");
			SessionErrors.add(actionRequest, "error-submit");
			e.printStackTrace();
		}
	}

	/**
	 * doUpdateDoc action method. Retrieve latest deadline data from user then
	 * insert in DB
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */

	/*
	 * 
	 * @ProcessAction(name = "doUpdateDoc") public void
	 * doUpdateDoc(ActionRequest actionRequest, ActionResponse actionResponse)
	 * throws IOException, PortletException {
	 * 
	 * System.out.
	 * println("======================== Start ======================");
	 * System.out.println("Retrieve data from update form...");
	 * System.out.println("..."); System.out.println("...");
	 * 
	 * long docId = ParamUtil.getLong(actionRequest, "docId"); String
	 * doc_deadline = ParamUtil.getString(actionRequest, "doc_deadline"); try {
	 * 
	 * System.out.println("Adding data to database...");
	 * 
	 * EntDoc doc = EntDocLocalServiceUtil.createEntDoc(docId);
	 * doc.setDoc_deadline(doc_deadline);
	 * 
	 * doc = EntDocLocalServiceUtil.updateEntDoc(doc);
	 * 
	 * System.out.println("Data added to database");
	 * System.out.println("======================== End ======================"
	 * );
	 * 
	 * // actionResponse.setRenderParameter("mvcPath", "/details.jsp");
	 * 
	 * } catch (SystemException e) {
	 * System.out.println("Failed to insert data to database");
	 * e.printStackTrace();
	 * System.out.println("======================== End ======================"
	 * );
	 * 
	 * }
	 * 
	 * }
	 */

}