package verifyDoc.portlet;

import verifyDoc.constants.VerifyDocPortletKeys;
import com._42Penguins.gosign.model.EntDoc;
import com._42Penguins.gosign.model.EntFileUpload;
import com._42Penguins.gosign.model.EntKey;
import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil;
import com._42Penguins.gosign.service.EntKeyLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
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
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
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
import javax.portlet.ProcessAction;

/**
 * @author Raziman Dom
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=verifyDoc Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + VerifyDocPortletKeys.VerifyDoc,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class VerifyDocPortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(VerifyDocPortlet.class);
	
	/**
	 * Action to get input action from user. All action will be redirect to sign/reject/justify
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws PortalException
	 */

	public void doActionMethod(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {
		
		_log.info("###################################################");
		_log.info("#               doActionMethod log                #");
		_log.info("###################################################");
		_log.info("START: doActionMethod Function");
		
		/*
		 *  Initiate variables
		 */
		
		_log.info("Fetch current document data");
		long docId = ParamUtil.getLong(actionRequest, "docId");
		String doAction = ParamUtil.getString(actionRequest, "doAction");
		EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
		String doc_status = doc.getDoc_status();
		
		/*
		 * Fetch current date and time
		 */
		
		_log.info("Fetch current time data");
		ZoneId zoneIdMYS = ZoneId.of("Asia/Kuala_Lumpur");
		LocalDateTime localDateTime = LocalDateTime.now(zoneIdMYS);
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss-A-N");
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String currentTime = localDateTime.format(formatterTime);
		String currentDate = localDateTime.format(formatterDate);
		
		/*
		 * Fetch current user data
		 */
		
		_log.info("Fetch current user data");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		String currentHomeURL = themeDisplay.getURLHome();
		
		/*
		 * List of status and action
		 */
		
		String statusPending = "Pending";
		String statusSigned = "Signed";
		String statusVerified = "Verified";
		String statusReject = "Rejected";
		String statusJustify = "Justify";
		String statusExpired = "Expired";
		
		String actionSign = "Sign";
		String actionReject = "Reject";
		String actionJustify = "Justify";
		
		/*
		 * Validation before redirect to sign, verify and justify method
		 */
		
		_log.info("Verify requested action: Sign/Reject/Justify");
		if (doAction.equals(actionSign)) {
			
			_log.info("###################################################");
			_log.info("#                Sign Document log                #");
			_log.info("###################################################");
			_log.info("START: Sign Document Function");
			
			if (doc_status.equals(statusPending) || doc_status.equals(statusJustify)) {
				
				/*
				 * Retrive user 6 pins. Standard length for user pin is 6 digits only
				 */
				
				_log.info("Retriving user's 6 pin");
				String userPin = ParamUtil.getString(actionRequest, "userPin");
				int userPinLength = 6;
				
				_log.info("Validating user's 6 pin");
				if (userPin.length() == userPinLength) {
					doSignDoc(userPin, currentUser, currentDate, currentTime, currentHomeURL, actionRequest, actionResponse);
				} else {
					_log.error("Invalid pin format");
					SessionErrors.add(actionRequest, "error-pin-invalid-format");
					actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
				}

			} else if (doc_status.equals(statusSigned) || doc_status.equals(statusReject) || doc_status.equals(statusExpired) || doc_status.equals(statusVerified)) {
				_log.warn("Cannot sign document that has been signed/rejected/expired/verified");
				SessionErrors.add(actionRequest, "error-status-fail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

			} else {
				_log.error("System unable to run sign function");
				SessionErrors.add(actionRequest, "error-sign-fail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			}

		} else if (doAction.equals(actionReject)) {
			_log.info("###################################################");
			_log.info("#              Reject Document log                #");
			_log.info("###################################################");
			_log.info("START: Reject Document Function");
			
			if (doc_status.equals(statusPending) || doc_status.equals(statusJustify)) {
				doRejectDoc(currentUser, currentDate, currentTime, currentHomeURL, actionRequest, actionResponse);

			} else if (doc_status.equals(statusSigned) || doc_status.equals(statusReject) || doc_status.equals(statusExpired) || doc_status.equals(statusVerified)) {
				_log.warn("Cannot reject document that has been signed/rejected/expired/verified");
				SessionErrors.add(actionRequest, "error-status-fail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

			} else {
				_log.error("System unable to run reject function");
				SessionErrors.add(actionRequest, "error-reject-fail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			}

		} else if (doAction.equals(actionJustify)) {
			_log.info("###################################################");
			_log.info("#       Request Justification Document log        #");
			_log.info("###################################################");
			_log.info("START: Request Justification Document Function");
			
			if (doc_status.equals(statusPending) || doc_status.equals(statusJustify)) {
				doJustifyDoc(currentUser, currentDate, currentTime, currentHomeURL, actionRequest, actionResponse);
				
			} else if (doc_status.equals(statusSigned) || doc_status.equals(statusReject) || doc_status.equals(statusExpired) || doc_status.equals(statusVerified)) {
				_log.warn("Cannot request justification for document that has been signed/rejected/expired/verified");
				SessionErrors.add(actionRequest, "error-status-fail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

			} else {
				_log.error("System unable run request justification function");
				SessionErrors.add(actionRequest, "error-justify-fail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			}

		} else {
			_log.error("System unable to identify requested action");
			SessionErrors.add(actionRequest, "error-status-invalid");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
		}

	}
	
	/**
	 * Action method to sign document with 6 digits pin
	 */

	public void doSignDoc(String userPin, User currentUser, String currentDate, String currentTime, String currentHomeURL, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		try {

			/*
			 *  Retrieve data from database for current user from EntKey entity
			 */
			_log.info("Fetching key data from database");
			long userId = currentUser.getUserId();
			EntKey genkey = EntKeyLocalServiceUtil.getEntKey(userId);
			String encodedEncryptedPriKey = genkey.getPrivatekey_Data();
			String encodedSalt = genkey.getSalt_Data();
			String encodedVector = genkey.getVector_Data();
			// String encodedPubKey = genkey.getPublickey_Data();

			/*
			 *  Retrieve data from database for current user from EntDoc entity
			 */
			_log.info("Fetching document data from database");
			long docId = ParamUtil.getLong(actionRequest, "docId");
			EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
			long signId = userId;
			String req_name = doc.getReq_name();
			String req_email = doc.getReq_email();
			String req_dateModified = currentDate;
			String req_timeModified = currentTime;
			String sign_name = currentUser.getFullName();
			String doc_type = doc.getDoc_type();
			String doc_md5 = doc.getDoc_md5();
			String doc_status = "Signed";
			Cipher dcipher;

			/*
			 * Decode private key, salt, and vector
			 */
			_log.info("Decoding encrypted private key, salt & vector");
			byte[] decodedEncryptedPriKey = Base64.getDecoder().decode(encodedEncryptedPriKey);
			byte[] decodedSalt = Base64.getDecoder().decode(encodedSalt);
			byte[] decodedVector = Base64.getDecoder().decode(encodedVector);

			/*
			 * Generating AES key
			 */
			_log.info("Generating AES key for decryption");
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec mykeySpec = new PBEKeySpec(userPin.toCharArray(), decodedSalt, 200000, 128);
			SecretKey tmp = factory.generateSecret(mykeySpec);
			SecretKeySpec mySecretkey = new SecretKeySpec(tmp.getEncoded(), "AES");
			IvParameterSpec vector = new IvParameterSpec(decodedVector);

			/*
			 * Create and initiate decryption using AES key
			 */
			_log.info("Initiate AES decryption");
			dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			dcipher.init(Cipher.DECRYPT_MODE, mySecretkey, vector);;

			/*
			 * Decrypt private key and get String
			 */
			_log.info("Decrypting private key");
			String decodedDecryptedPriKey = new String(dcipher.doFinal(decodedEncryptedPriKey));
			byte[] FinaldecodedDecryptedPriKey = Base64.getDecoder().decode(decodedDecryptedPriKey);

			/*
			 * Get raw private key for signing
			 */
			_log.info("Start signing document");
			KeyFactory kf = KeyFactory.getInstance("EC"); // or "EC" or whatever
			PrivateKey rawPrivateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(FinaldecodedDecryptedPriKey));

			_log.info("Signing document MD5");
			Signature signature = Signature.getInstance("SHA1withECDSA");
			byte[] bytes = doc_md5.getBytes("UTF8");
			signature.initSign(rawPrivateKey);
			signature.update(bytes);
			byte[] digitalSignature = signature.sign();
			String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);

			/*
			 * Insert data to database
			 */
			
			_log.info("Inserting data to database");
			doc.setReq_dateModified(req_dateModified);
			doc.setReq_timeModified(req_timeModified);
			doc.setDoc_signature(encodedSignature);
			doc.setDoc_status(doc_status);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			doc = EntDocLocalServiceUtil.updateEntDoc(doc);

			/*
			 * Function to send email for requester
			 */
			_log.info("Sending email to requester");

			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;

			fromAddress = new InternetAddress("noreply@myiexplorer.com");
			toAddress = new InternetAddress(req_email);
			MailMessage mailMessage = new MailMessage();

			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("GoSign Signature Status - Approved");

			// mailMessage.setBody(body);

			mailMessage.setBody("" + "<font face=\"arial\" size=\"2\">" + "<p>Dear " + req_name + ",</p> "
					+ "<p>Your signature request has been approved. Please login to verify the signature.</p>"
					+ "<p><strong>Request Details:</strong></p>" + "<table style=\"font-family:arial;font-size:13px\">"
					+ "<tr><td>Request ID:</td><td> " + docId + "</td></tr>" + "<tr><td>Request Type:</td><td> "
					+ doc_type + "</td></tr>" + "<tr><td>Request Status:</td><td> " + doc_status + "</td></tr>"
					+ "<tr><td>Signed By:</td><td> " + sign_name + "</td></tr>" + "<tr><td>Signed On:</td><td> "
					+ req_dateModified + "</td></tr>" + "</table>" + "<p><a href=\"" + currentHomeURL
					+ "/upload-document" + "\"> Click here to view uploaded document</a></p><br>"
					+ "<p>Sincerely,<br>GoSign Team<br>" + "<a href=\"" + currentHomeURL + "\"> " + currentHomeURL
					+ "</a></p>" + "</font>");

			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			
			_log.info("COMPLETED: Signed document: " + docId);
			SessionMessages.add(actionRequest, "request_processed",
					"Document has been signed! A notification email has been sent to requester.");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

		} catch (Exception e) {
			_log.error("Unable to sign the document.");
			e.printStackTrace();
			SessionErrors.add(actionRequest, "error-sign-fail");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
		}


	}
	/*
	 * Reject Signature Module
	 */

	public void doRejectDoc(User currentUser, String currentDate, String currentTime, String currentHomeURL, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {


		_log.info("Fetching data from database");
		
		long docId = ParamUtil.getLong(actionRequest, "docId");
		long signId = (currentUser.getUserId());
		EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
		String req_name = doc.getReq_name();
		String req_email = doc.getReq_email();
		String req_dateModified = currentDate;
		String req_timeModified = currentTime;
		String sign_name = currentUser.getFullName();
		String doc_type = doc.getDoc_type();
		String doc_status = "Rejected";

		try {
			
			_log.info("Rejecting document");
			
			doc.setReq_dateModified(req_dateModified);
			doc.setReq_timeModified(req_timeModified);
			doc.setDoc_status(doc_status);
			doc = EntDocLocalServiceUtil.updateEntDoc(doc);

			/*
			 * Function to send email for requester
			 */
			
			_log.info("Sending email to requester");
			
			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;

			fromAddress = new InternetAddress("noreply@myiexplorer.com");
			toAddress = new InternetAddress(req_email);
			MailMessage mailMessage = new MailMessage();

			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("GoSign Signature Status - Disapproved");

			// mailMessage.setBody(body);

			mailMessage.setBody("" + "<font face=\"arial\" size=\"2\">" + "<p>Dear " + req_name + ",</p> "
					+ "<p>Your signature request has been disapproved.</p>" + "<p><strong>Request Details:</strong></p>"
					+ "<table style=\"font-family:arial;font-size:13px\">" + "<tr><td>Request ID:</td><td> " + docId
					+ "</td></tr>" + "<tr><td>Request Type:</td><td> " + doc_type + "</td></tr>"
					+ "<tr><td>Request Status:</td><td> " + doc_status + "</td></tr>" + "<tr><td>Rejected By:</td><td> "
					+ sign_name + "</td></tr>" + "<tr><td>Rejected On:</td><td> " + req_dateModified + "</td></tr>"
					+ "</table>" + "<p><a href=\"" + currentHomeURL + "/upload-document"
					+ "\"> Click here to view uploaded document</a></p><br>" + "<p>Sincerely,<br>GoSign Team<br>"
					+ "<a href=\"" + currentHomeURL + "\"> " + currentHomeURL + "</a></p>" + "</font>");

			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			_log.info("Email has been sent to requester!");
			_log.info("COMPLETED: Rejected document: " + docId);
			SessionMessages.add(actionRequest, "request_processed", "You have rejected the signature request.");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

		} catch (Exception e) {
			_log.error("Fail to reject document");
			e.printStackTrace();
			SessionErrors.add(actionRequest, "error-reject-fail");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
		}

	}

	/*
	 * Justification Signature Module
	 */

	public void doJustifyDoc(User currentUser, String currentDate, String currentTime, String currentHomeURL, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {

		_log.info("Retrieving data from database");
		long docId = ParamUtil.getLong(actionRequest, "docId");
		long signId = (currentUser.getUserId());
		EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
		String req_name = doc.getReq_name();
		String req_email = doc.getReq_email();
		String req_dateModified = currentDate;
		String req_timeModified = currentTime;
		String sign_name = currentUser.getFullName();
		String doc_type = doc.getDoc_type();
		String doc_status = "Justify";

		try {
			
			_log.info("Requesting document justification");
			
			doc.setReq_timeModified(req_timeModified);
			doc.setReq_dateModified(req_dateModified);
			doc.setDoc_status(doc_status);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			_log.info("Inserting data to DB");

			doc = EntDocLocalServiceUtil.updateEntDoc(doc);

			/*
			 * Function to send email for requester
			 */
			
			_log.info("Sending email to requester");
			
			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;

			String justificationMsg = ParamUtil.getString(actionRequest, "justificationMsg");

			fromAddress = new InternetAddress("noreply@myiexplorer.com");
			toAddress = new InternetAddress(req_email);
			MailMessage mailMessage = new MailMessage();

			mailMessage.setTo(toAddress);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("GoSign Signature Status - Pending Justification");

			// mailMessage.setBody(body);

			mailMessage.setBody(""

					+ "<font face=\"arial\" size=\"2\">"
					+ "<p>Dear " + req_name + ",</p> "
					+ "<p>Please provide more justification on this signature request.</p>"
					+ "<p><strong>Request Details:</strong></p>"
					+ "<table style=\"font-family:arial;font-size:13px\">"
					+ "<tr><td>Request ID:</td><td> " + docId + "</td></tr>"
					+ "<tr><td>Request Type:</td><td> " + doc_type + "</td></tr>"
					+ "<tr><td>Request Status:</td><td> " + doc_status + "</td></tr>"
					+ "<tr><td>Signer:</td><td> " + sign_name + "</td></tr>"
					+ "<tr><td>Signer Comments:</td><td> "+ justificationMsg + "</td></tr>"
					+ "</table>" 
					+ "<p><a href=\"" + currentHomeURL + "/upload-document" + "\"> "
					+ "Click here to view uploaded document"
					+ "</a></p>"
					+ "<br>"
					+ "<p>Sincerely,<br>"
					+ "GoSign Team<br>" 
					+ "<a href=\"" + currentHomeURL + "\"> " + currentHomeURL
					+ "</a></p>" + "</font>");

			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);
			_log.info("Email has been sent to requester!");
			_log.info("COMPLETED: Requested justification document: " + docId);
			SessionMessages.add(actionRequest, "request_processed",
					"Email has been sent to requester to request for more justification.");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

		} catch (Exception e) {
			_log.error("Fail to request document justification");
			e.printStackTrace();
			SessionErrors.add(actionRequest, "error-justify-fail");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
		}

	}
	
	/*
	 * Accept document method to fetch signer ID
	 */
	
	@ProcessAction(name = "doAcceptAction")
	public void doAcceptAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {
		
		_log.info("###################################################");
		_log.info("#                Accept Document log              #");
		_log.info("###################################################");
		_log.info("START: Accept Document Function");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		long docId = ParamUtil.getLong(actionRequest, "docId");
		long signId = (currentUser.getUserId());
		String sign_name = currentUser.getFullName();
	
			try {
				
				_log.info("Accepting document");
					
				EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
				doc.setSignId(signId);
				doc.setSign_name(sign_name);
				doc.setReq_accepted(true);
		
				doc = EntDocLocalServiceUtil.updateEntDoc(doc);
				
				_log.info("COMPLETED: Accepted document: " + docId);
				SessionMessages.add(actionRequest, "request_accepted",
							"You have accepted this signature request.");
		
				} catch (Exception e) {
					_log.error("Fail to accept document");
					e.printStackTrace();
					actionResponse.setRenderParameter("mvcPath", "/view.jsp");
				}

		
	}

	/*
	 * serveResource method is used for displaying blob data
	 */

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		try {

			long dataId = ParamUtil.getLong(resourceRequest, "dataId");

			EntFileUpload fileup = EntFileUploadLocalServiceUtil.getEntFileUpload(dataId);

			if (fileup != null) {
				Blob blob = fileup.getFile_blob();
				byte[] binaryData = blob.getBytes(1, (int) blob.length());
				// resourceResponse.setContentType(blobDemo.getMimeType());
				resourceResponse.setContentType("application/application-download");
				resourceResponse.setProperty("Content-disposition", "attachement; filename=" + fileup.getFile_name());
				OutputStream o = resourceResponse.getPortletOutputStream();
				o.write(binaryData);
				o.flush();
				o.close();
				resourceResponse.flushBuffer();
			}

		} catch (Exception e) {

		}
	}
	
	
	/*
	@ProcessAction(name = "doAcceptAction")
	public void doAcceptAction(ActionRequest actionRequest, ActionResponse actionResponse) {
	}*/
	
}