package signDoc.portlet;

import signDoc.constants.SignDocPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import DocRegistration.model.Document;
import DocRegistration.model.GenKey;
import DocRegistration.service.DocumentLocalServiceUtil;
import DocRegistration.service.GenKeyLocalServiceUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
@Component(immediate = true, property = { "com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=signDoc Portlet", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + SignDocPortletKeys.SignDoc,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class SignDocPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		renderRequest.setAttribute("currentEmail", currentUser.getEmailAddress());

		// System.out.println(currentUser);

		// String currentHomeURL = themeDisplay.getURLHome();
		// System.out.println("currenthomeurl: " +currentHomeURL);

		// String currentCompURL = PortletURLUtil.getCurrent(renderRequest,
		// renderResponse).toString();
		// System.out.println(currentCompURL);

		// LocalDateTime localDateTime = LocalDateTime.now();
		// DateTimeFormatter formatter =
		// DateTimeFormatter.ofPattern("dd-MM-yyyy");
		// String formatDateTime = localDateTime.format(formatter);

		// renderRequest.setAttribute("currentUserId", currentUser.getUserId());
		// renderRequest.setAttribute("currentFirstName",
		// currentUser.getFirstName());

		// renderRequest.setAttribute("currentDateTime", formatDateTime);
		// renderRequest.setAttribute("currentCompURL", currentCompURL);
		// renderRequest.setAttribute("currentHomeURL", currentHomeURL);

		super.doView(renderRequest, renderResponse);

	}

	public void doBack(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		System.out.println("Go back to view page.");
		actionResponse.setRenderParameter("mvcPath", "/view.jsp");

	}

	public void doSignAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {
		
		//==> Initiate variables
		long docId = ParamUtil.getLong(actionRequest, "docId");
		String doAction = ParamUtil.getString(actionRequest, "doAction");
		Document doc = DocumentLocalServiceUtil.getDocument(docId);
		String doc_status = doc.getDoc_status();
		
		//==> Get current date & time
		ZoneId zoneIdMYS = ZoneId.of("Asia/Kuala_Lumpur");
		LocalDateTime localDateTime = LocalDateTime.now(zoneIdMYS);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String currentDateTime = localDateTime.format(formatter);

		//==> Retrieve user pin parameter
		String userPin = ParamUtil.getString(actionRequest, "userPin");

		//==> Standard length for user pin is 6 digits only
		int userPinLength = 6;

		//==> List of status
		String statusPending = "Pending";
		String statusSigned = "Signed";
		String statusReject = "Rejected";
		String statusJustify = "Justify";

		//==> List of action
		String actionSign = "Sign";
		String actionReject = "Reject";
		String actionJustify = "Justify";
		
		//==> Validation before redirect to sign, verify and justify method
		if (doAction.equals(actionSign)) {

			if (doc_status.equals(statusPending) || doc_status.equals(statusJustify)) {

				if (userPin.length() == userPinLength) {
					doSignDoc(userPin, currentDateTime, actionRequest, actionResponse);
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
				doRejectDoc(currentDateTime, actionRequest, actionResponse);

			} else if (doc_status.equals(statusSigned) || doc_status.equals(statusReject)) {
				SessionErrors.add(actionRequest, "error-key-statusFail");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");

			} else {
				SessionErrors.add(actionRequest, "error-key-statusInvalid");
				actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			}

		} else if (doAction.equals(actionJustify)) {

			if (doc_status.equals(statusPending) || doc_status.equals(statusJustify)) {
				doJustifyDoc(currentDateTime, actionRequest, actionResponse);

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

	public void doSignDoc(String userPin, String currentDateTime, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		System.out.println("================>>> START - doSignDoc");

		// ==> Start signing if user entered 6 digits
		try {

			// ==> Get current user
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User currentUser = themeDisplay.getUser();
			String currentHomeURL = themeDisplay.getURLHome();

			System.out.println("\n================>>> START: Decrypting private key using AES 128 bit\n");

			// ==> Retrieve some data from database for current user from genkey
			// table
			System.out.println("Retrieving data from database...");
			long userId = currentUser.getUserId();
			GenKey genkey = GenKeyLocalServiceUtil.getGenKey(userId);
			String encodedEncryptedPriKey = genkey.getPrivatekey_Data();
			String encodedSalt = genkey.getSalt_Data();
			String encodedVector = genkey.getVector_Data();
			// String encodedPubKey = genkey.getPublickey_Data();

			// ==> Retrieve some data from database for current user from document table
			long docId = ParamUtil.getLong(actionRequest, "docId");
			Document doc = DocumentLocalServiceUtil.getDocument(docId);
			String req_name = doc.getReq_name();
			String req_email = doc.getReq_email();
			String req_dateModified = currentDateTime;
			String sign_name = currentUser.getFullName();
			String doc_type = doc.getDoc_type();
			String req_md5 = doc.getFile_md5();
			String doc_status = "Signed";

			Cipher dcipher;

			// ==> Decode private key, salt, and vector
			System.out.println("Decoding encrypted private key...");
			byte[] decodedEncryptedPriKey = Base64.getDecoder().decode(encodedEncryptedPriKey);
			System.out.println("Decoding Salt...");
			byte[] decodedSalt = Base64.getDecoder().decode(encodedSalt);
			System.out.println("Decoding Vector...");
			byte[] decodedVector = Base64.getDecoder().decode(encodedVector);

			// Generating AES key
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec mykeySpec = new PBEKeySpec(userPin.toCharArray(), decodedSalt, 10000, 128);
			SecretKey tmp = factory.generateSecret(mykeySpec);
			SecretKeySpec mySecretkey = new SecretKeySpec(tmp.getEncoded(), "AES");
			IvParameterSpec vector = new IvParameterSpec(decodedVector);

			// ==> Create and initiate decryption using AES key
			System.out.println("Initiate decryption alogrithm...");
			dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// System.out.println("Algorithm to decrypt private key: " +
			// dcipher);
			dcipher.init(Cipher.DECRYPT_MODE, mySecretkey, vector);
			// System.out.println("Secret key: " + mySecretkey);

			// ==> Decrypt private key and get String
			System.out.println("Decrypting private key...");
			String decodedDecryptedPriKey = new String(dcipher.doFinal(decodedEncryptedPriKey));
			// System.out.println("Decrypted PrivateKey:
			// "+decodedDecryptedPriKey);
			byte[] FinaldecodedDecryptedPriKey = Base64.getDecoder().decode(decodedDecryptedPriKey);

			System.out.println("\n================>>> END: Decrypting private key using AES 128 bit\n");

			System.out.println("\n================>>> START - SIGN DOCUMENT\n");

			// ==> Get raw private key for signing
			KeyFactory kf = KeyFactory.getInstance("EC"); // or "EC" or whatever
			PrivateKey rawPrivateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(FinaldecodedDecryptedPriKey));
			// System.out.println("Raw Private Key: "+rawPrivateKey);

			System.out.println("Signing MD5...");
			Signature signature = Signature.getInstance("SHA1withECDSA");
			byte[] bytes = req_md5.getBytes("UTF8");
			signature.initSign(rawPrivateKey);
			signature.update(bytes);
			byte[] digitalSignature = signature.sign();
			String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);

			System.out.println("Sign completed!");

			System.out.println("\n================>>> END - SIGN DOCUMENT\n");

			// ==> Insert data to database
			System.out.println("Inserting data to DB...");
			doc.setReq_dateModified(req_dateModified);
			doc.setReq_signature(encodedSignature);
			doc.setDoc_status(doc_status);
			doc.setSign_name(sign_name);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			doc = DocumentLocalServiceUtil.updateDocument(doc);

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

	public void doRejectDoc(String currentDateTime, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {

		System.out.println("================Start doRejectDoc=================");

		// ==> Get current user
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		String currentHomeURL = themeDisplay.getURLHome();

		// ==> Retrieve some data from database for current user from document
		// table
		System.out.println("Retrieving data from database...");
		long docId = ParamUtil.getLong(actionRequest, "docId");
		Document doc = DocumentLocalServiceUtil.getDocument(docId);
		String req_name = doc.getReq_name();
		String req_email = doc.getReq_email();
		String req_dateModified = currentDateTime;
		String sign_name = currentUser.getFullName();
		String doc_type = doc.getDoc_type();
		String doc_status = "Rejected";

		try {

			doc.setSign_name(sign_name);
			doc.setDoc_status(doc_status);
			System.out.println("Status updated: " + doc_status);
			System.out.println("Inserting data to DB");

			doc = DocumentLocalServiceUtil.updateDocument(doc);

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

	public void doJustifyDoc(String currentDateTime, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException {

		System.out.println("================Start doReqJustification=================");

		// ==> Get current user
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		String currentHomeURL = themeDisplay.getURLHome();

		// ==> Retrieve some data from database for current user from document
		// table
		System.out.println("Retrieving data from database...");
		long docId = ParamUtil.getLong(actionRequest, "docId");
		Document doc = DocumentLocalServiceUtil.getDocument(docId);
		String req_name = doc.getReq_name();
		String req_email = doc.getReq_email();
		String req_dateModified = currentDateTime;
		String sign_name = currentUser.getFullName();
		String doc_type = doc.getDoc_type();
		String doc_status = "Justify";

		try {

			doc.setReq_dateModified(req_dateModified);
			doc.setSign_name(sign_name);
			doc.setDoc_status(doc_status);
			actionResponse.setRenderParameter("mvcPath", "/viewDetails.jsp");
			System.out.println("Status updated: " + doc_status);
			System.out.println("Inserting data to DB");

			doc = DocumentLocalServiceUtil.updateDocument(doc);

			/*
			 * Function to send email for requestor
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

					+ "<font face=\"arial\" size=\"2\">" + "<p>Dear " + req_name + ",</p> "
					+ "<p>Please provide more justification on this signature request.</p>"
					+ "<p><strong>Request Details:</strong></p>" + "<table style=\"font-family:arial;font-size:13px\">"
					+ "<tr><td>Request ID:</td><td> " + docId + "</td></tr>" + "<tr><td>Request Type:</td><td> "
					+ doc_type + "</td></tr>" + "<tr><td>Request Status:</td><td> " + doc_status + "</td></tr>"
					+ "<tr><td>Signer:</td><td> " + sign_name + "</td></tr>" + "<tr><td>Signer Comments:</td><td> "
					+ justificationMsg + "</td></tr>" + "</table>" + "<p><a href=\"" + currentHomeURL
					+ "/upload-document" + "\"> Click here to view uploaded document</a></p><br>"
					+ "<p>Sincerely,<br>GoSign Team<br>" + "<a href=\"" + currentHomeURL + "\"> " + currentHomeURL
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
	 * Here serveResource method is used for displaying blob data
	 *
	 */

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		try {

			long dataId = ParamUtil.getLong(resourceRequest, "dataId");

			Document doc = DocumentLocalServiceUtil.getDocument(dataId);

			if (doc != null) {
				Blob blob = doc.getFile_blob();
				byte[] binaryData = blob.getBytes(1, (int) blob.length());
				// resourceResponse.setContentType(blobDemo.getMimeType());
				resourceResponse.setContentType("application/application-download");
				resourceResponse.setProperty("Content-disposition", "attachement; filename=" + doc.getFile_name());
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