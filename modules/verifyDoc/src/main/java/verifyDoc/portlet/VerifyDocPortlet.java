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
	

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		renderRequest.setAttribute("currentEmail", currentUser.getEmailAddress());

		super.doView(renderRequest, renderResponse);

	}
	
	/**
	 * Action to get input action from user. All action will be redirect to sign/reject/justify
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws PortalException
	 */

	public void doSignAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {
		
		/*
		 *  Initiate variables
		 */
		
		long docId = ParamUtil.getLong(actionRequest, "docId");
		String doAction = ParamUtil.getString(actionRequest, "doAction");
		EntDoc doc = EntDocLocalServiceUtil.getEntDoc(docId);
		String doc_status = doc.getDoc_status();
		
		/*
		 * Fetch current date and time
		 */
		
		ZoneId zoneIdMYS = ZoneId.of("Asia/Kuala_Lumpur");
		LocalDateTime localDateTime = LocalDateTime.now(zoneIdMYS);
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss-A-N");
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String currentTime = localDateTime.format(formatterTime);
		String currentDate = localDateTime.format(formatterDate);
		
		/*
		 * Fetch current user data
		 */
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		String currentHomeURL = themeDisplay.getURLHome();
		
		/*
		 * Retrive user 6 pins. Standard length for user pin is 6 digits only
		 */
		
		String userPin = ParamUtil.getString(actionRequest, "userPin");
		int userPinLength = 6;

		
		/*
		 * List of status and action
		 */
		
		String statusPending = "Pending";
		String statusSigned = "Signed";
		String statusReject = "Rejected";
		String statusJustify = "Justify";
		
		String actionSign = "Sign";
		String actionReject = "Reject";
		String actionJustify = "Justify";
		
		/*
		 * Validation before redirect to sign, verify and justify method
		 */
		
		if (doAction.equals(actionSign)) {

			if (doc_status.equals(statusPending) || doc_status.equals(statusJustify)) {

				if (userPin.length() == userPinLength) {
					doSignDoc(userPin, currentUser, currentDate, currentTime, currentHomeURL, actionRequest, actionResponse);
				} else {
					SessionErrors.add(actionRequest, "error-key-invalidPinFormat");
					actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
				}

			} else if (doc_status.equals(statusSigned) || doc_status.equals(statusReject)) {
				SessionErrors.add(actionRequest, "error-key-statusFail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

			} else {
				SessionErrors.add(actionRequest, "error-key-statusInvalid");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			}

		} else if (doAction.equals(actionReject)) {

			if (doc_status.equals(statusPending) || doc_status.equals(statusJustify)) {
				doRejectDoc(currentUser, currentDate, currentTime, currentHomeURL, actionRequest, actionResponse);

			} else if (doc_status.equals(statusSigned) || doc_status.equals(statusReject)) {
				SessionErrors.add(actionRequest, "error-key-statusFail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

			} else {
				SessionErrors.add(actionRequest, "error-key-statusInvalid");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			}

		} else if (doAction.equals(actionJustify)) {

			if (doc_status.equals(statusPending) || doc_status.equals(statusJustify)) {
				doJustifyDoc(currentUser, currentDate, currentTime, currentHomeURL, actionRequest, actionResponse);

			} else if (doc_status.equals(statusSigned) || doc_status.equals(statusReject)) {
				SessionErrors.add(actionRequest, "error-key-statusFail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

			} else {
				SessionErrors.add(actionRequest, "error-key-statusInvalid");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			}

		} else {
			SessionErrors.add(actionRequest, "error-key-statusInvalid");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
		}

	}
	
	/**
	 * Action method to sign document with 6 digits pin
	 */

	public void doSignDoc(String userPin, User currentUser, String currentDate, String currentTime, String currentHomeURL, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		System.out.println("================>>> START - doSignDoc");

		try {

			System.out.println("\n================>>> START: Decrypting private key using AES 128 bit\n");

			/*
			 *  Retrieve data from database for current user from EntKey entity
			 */
			System.out.println("Retrieving data from database...");
			long userId = currentUser.getUserId();
			EntKey genkey = EntKeyLocalServiceUtil.getEntKey(userId);
			String encodedEncryptedPriKey = genkey.getPrivatekey_Data();
			String encodedSalt = genkey.getSalt_Data();
			String encodedVector = genkey.getVector_Data();
			// String encodedPubKey = genkey.getPublickey_Data();

			/*
			 *  Retrieve data from database for current user from EntDoc entity
			 */
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
			System.out.println("Decoding encrypted private key...");
			byte[] decodedEncryptedPriKey = Base64.getDecoder().decode(encodedEncryptedPriKey);
			System.out.println("Decoding Salt...");
			byte[] decodedSalt = Base64.getDecoder().decode(encodedSalt);
			System.out.println("Decoding Vector...");
			byte[] decodedVector = Base64.getDecoder().decode(encodedVector);

			/*
			 * Generating AES key
			 */
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec mykeySpec = new PBEKeySpec(userPin.toCharArray(), decodedSalt, 200000, 128);
			SecretKey tmp = factory.generateSecret(mykeySpec);
			SecretKeySpec mySecretkey = new SecretKeySpec(tmp.getEncoded(), "AES");
			IvParameterSpec vector = new IvParameterSpec(decodedVector);

			/*
			 * Create and initiate decryption using AES key
			 */
			System.out.println("Initiate decryption alogrithm...");
			dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// System.out.println("Algorithm to decrypt private key: " +
			// dcipher);
			dcipher.init(Cipher.DECRYPT_MODE, mySecretkey, vector);
			// System.out.println("Secret key: " + mySecretkey);

			/*
			 * Decrypt private key and get String
			 */
			System.out.println("Decrypting private key...");
			String decodedDecryptedPriKey = new String(dcipher.doFinal(decodedEncryptedPriKey));
			// System.out.println("Decrypted PrivateKey:
			// "+decodedDecryptedPriKey);
			byte[] FinaldecodedDecryptedPriKey = Base64.getDecoder().decode(decodedDecryptedPriKey);

			System.out.println("\n================>>> END: Decrypting private key using AES 128 bit\n");

			System.out.println("\n================>>> START - SIGN DOCUMENT\n");

			/*
			 * Get raw private key for signing
			 */
			KeyFactory kf = KeyFactory.getInstance("EC"); // or "EC" or whatever
			PrivateKey rawPrivateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(FinaldecodedDecryptedPriKey));
			// System.out.println("Raw Private Key: "+rawPrivateKey);

			System.out.println("Signing MD5...");
			Signature signature = Signature.getInstance("SHA1withECDSA");
			byte[] bytes = doc_md5.getBytes("UTF8");
			signature.initSign(rawPrivateKey);
			signature.update(bytes);
			byte[] digitalSignature = signature.sign();
			String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);

			System.out.println("Sign completed!");

			System.out.println("\n================>>> END - SIGN DOCUMENT\n");

			/*
			 * Insert data to database
			 */
			
			System.out.println("Inserting data to DB...");
			doc.setReq_dateModified(req_dateModified);
			doc.setReq_timeModified(req_timeModified);
			doc.setDoc_signature(encodedSignature);
			doc.setDoc_status(doc_status);
			doc.setSignId(signId);
			doc.setSign_name(sign_name);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			doc = EntDocLocalServiceUtil.updateEntDoc(doc);

			/*
			 * Function to send email for requestor
			 */

			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;

			fromAddress = new InternetAddress("noreply@42penguins.com");
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
			System.out.println("Email has been sent to requestor!");
			SessionMessages.add(actionRequest, "request_processed",
					"Document has been signed! A notification email has been sent to requestor.");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

		} catch (Exception e) {
			System.out.println("Failed to sign document...");
			e.printStackTrace();
			SessionErrors.add(actionRequest, "error-key-signFail");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
		}

		System.out.println("\n================>>> END - doSignDoc\n");

	}
	/*
	 * Reject Signature Module
	 */

	public void doRejectDoc(User currentUser, String currentDate, String currentTime, String currentHomeURL, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {

		System.out.println("================Start doRejectDoc=================");

		// ==> Retrieve some data from database for current user from document
		// table
		System.out.println("Retrieving data from database...");
		
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
			
			doc.setSignId(signId);
			doc.setSign_name(sign_name);
			doc.setReq_dateModified(req_dateModified);
			doc.setReq_timeModified(req_timeModified);
			doc.setDoc_status(doc_status);
			System.out.println("Status updated: " + doc_status);
			System.out.println("Inserting data to DB");

			doc = EntDocLocalServiceUtil.updateEntDoc(doc);

			/*
			 * Function to send email for requestor
			 */

			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;

			fromAddress = new InternetAddress("noreply@42penguins.com");
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
			System.out.println("Email has been sent to requestor!");
			SessionMessages.add(actionRequest, "request_processed", "You have rejected the signature request.");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

		} catch (Exception e) {
			System.out.println("Fail to reject document...");
			e.printStackTrace();
			SessionErrors.add(actionRequest, "error-key-statusFail");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
		}

		System.out.println("================End doRejectDoc=================");
	}

	/*
	 * Justification Signature Module
	 */

	public void doJustifyDoc(User currentUser, String currentDate, String currentTime, String currentHomeURL, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {

		System.out.println("================Start doReqJustification=================");

		// ==> Retrieve some data from database for current user from document
		// table
		System.out.println("Retrieving data from database...");
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
			
			doc.setSignId(signId);
			doc.setReq_timeModified(req_timeModified);
			doc.setReq_dateModified(req_dateModified);
			doc.setSign_name(sign_name);
			doc.setDoc_status(doc_status);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			System.out.println("Status updated: " + doc_status);
			System.out.println("Inserting data to DB");

			doc = EntDocLocalServiceUtil.updateEntDoc(doc);

			/*
			 * Function to send email for requester
			 */

			InternetAddress fromAddress = null;
			InternetAddress toAddress = null;

			String justificationMsg = ParamUtil.getString(actionRequest, "justificationMsg");

			fromAddress = new InternetAddress("noreply@42penguins.com");
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
			System.out.println("Email has been sent to requestor!");
			SessionMessages.add(actionRequest, "request_processed",
					"Email has been sent to requestor to request for more justification.");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

		} catch (Exception e) {
			System.out.println("Fail to sign document...");
			e.printStackTrace();
			SessionErrors.add(actionRequest, "error-key-statusFail");
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
		}

		System.out.println("================End doReqJustification=================");
	}
	

	/*
	 * 
	 * serveResource method is used for displaying blob data
	 *
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
	
}