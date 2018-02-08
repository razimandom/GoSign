package genKey.portlet;

import genKey.constants.GenKeyPortletKeys;
import com._42Penguins.gosign.model.EntKey;
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
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.KeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import org.osgi.service.component.annotations.Component;

/**
 * @author Raziman Dom
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=goSign",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=genKey Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GenKeyPortletKeys.GenKey,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GenKeyPortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(GenKeyPortlet.class);
	
	/**
	 * doActionMethod method validate if the 6 pin digits is valid. If valid, proceed to doGenKey method
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws NoSuchAlgorithmException
	 */
	
	@ProcessAction(name = "doActionMethod")
	public void doActionMethod(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, NoSuchAlgorithmException {
		
		_log.info("###################################################");
		_log.info("#               doActionMethod log                #");
		_log.info("###################################################");
		_log.info("START: doActionMethod Function");
		
    	ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		
		/*
		 *  Initiate variables
		 */
		
		String doAction = ParamUtil.getString(actionRequest, "doAction");
		String doGenKey = "genkey";
		String doDelKey = "delkey";
		
		/*
		 * Validation before redirect to generate key method
		 */
		
		_log.info("Verify requested action: Generate key");
		
		if (doAction.equals(doGenKey)){
			
			_log.info("Verify entered 6 pins");
			/*
			 *  Retrieve user pin parameter
			 */
			String userPin = ParamUtil.getString(actionRequest, "userPin");

			/*
			 *  Standard length for user pin is 6 digits only
			 */
			int userPinLength = 6;
			
			_log.info("Check pin format");
			
			if (userPin.length() == userPinLength) {
				
				_log.info("###################################################");
				_log.info("#                  doGenKey log                   #");
				_log.info("###################################################");
				_log.info("START: Generate Key Function");
				doGenKey(currentUser, actionRequest, actionResponse);
				
			} else {
				_log.error("Invalid pin format");
				SessionErrors.add(actionRequest, "error-key-invalidPinFormat");
			}
			
		} else if (doAction.equals(doDelKey)){
				_log.info("Deleting public and private key");
				doDelKey(currentUser, actionRequest, actionResponse);
				
		}	else{
			_log.error("System unable to identify requested action");
			SessionErrors.add(actionRequest, "error-key-actionError");
		}
		
	}
	
	/**
	 * Generate public and private key using ECC. Encrypt the private key using AES algorithm.
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	
	@ProcessAction(name = "doGenKey")
	public void doGenKey(User currentUser, ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		
        try {
            
			/*
			 * Fetch current date and time
			 */

			_log.info("Fetching current date");
    		ZoneId zoneIdMYS = ZoneId.of("Asia/Kuala_Lumpur");
    		LocalDateTime localDateTime = LocalDateTime.now(zoneIdMYS);
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    		String currentDateTime = localDateTime.format(formatter);
    		
			/*
			 * Fetch current user data
			 */

			_log.info("Fetching data of current logged user");
    		long currentUserId = currentUser.getUserId();
    		
    		/*
    		 * Initiate ECC Algorithm to create key
    		 */
    		
    		_log.info("Preparing ECC algorithm for key generation");
    		
            KeyPairGenerator kpg;
            kpg = KeyPairGenerator.getInstance("EC","SunEC");
            ECGenParameterSpec ecsp;
            ecsp = new ECGenParameterSpec("sect163k1");
            kpg.initialize(ecsp);
            
            /*
             * Generate public and private key for ECC
             */
            
            _log.info("Generating ECC Key");
            KeyPair kp = kpg.genKeyPair();
            PrivateKey privKey = kp.getPrivate();
            PublicKey pubKey = kp.getPublic();
            
            /*
             * Encode private and public key
             */
            
            _log.info("Encoding private and public keys");
            byte[] privateKeyBytes = privKey.getEncoded();
            byte[] publicKeyBytes = pubKey.getEncoded(); 
    		
            String encodedECCprivateKeyBytes = Base64.getEncoder().encodeToString(privateKeyBytes);
            String encodedECCpublicKeyBytes = Base64.getEncoder().encodeToString(publicKeyBytes);
    		
            _log.info("Generating key process completed");
            
            /*
             * Encryption of private key
             */
             
            _log.info("Encrypting private key");
          
            /*
             * Generate salt
             */
            
            _log.info("Generating random salt with 32 bits");     
            Random r = new SecureRandom();
    		byte[] salt = new byte[32];
    		r.nextBytes(salt);
    		
    		/*
    		 * Initialize iteration
    		 */

    		int iteration = 200000;
    		_log.info("Initialize iteration count: " + iteration);
    		
    		/*
    		 * Initialize bits
    		 */
    		
    		int bit = 128;
    		_log.info("Initialize bit count: " + bit);
    		
    		/*
    		 * Initialize vector
    		 */
    		
    		_log.info("Generate random vector with " + bit + "bit");
    		byte[] vector = new byte[128/8];
    		r.nextBytes(vector);
    		IvParameterSpec ivspec = new IvParameterSpec(vector);
    		
    		/*
    		 * Initialize variables
    		 */
    		String MsgToEncrypt = encodedECCprivateKeyBytes;
            String userPin = ParamUtil.getString(actionRequest, "userPin");
            Cipher ecipher;
    		
            /*
             * Generating AES key for encryption
             */
            
            _log.info("Generating AES key for encryption");
            SecretKeyFactory factory =  SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec mykeySpec = new PBEKeySpec(userPin.toCharArray(), salt, iteration, bit);
            SecretKey tmp = factory.generateSecret(mykeySpec);
            SecretKeySpec mySecretkey = new SecretKeySpec(tmp.getEncoded(), "AES");
            
            /*
             *  Create and initiate encryption
             */
            
            _log.info("Initiate encryption alogrithm");
            ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ecipher.init(Cipher.ENCRYPT_MODE, mySecretkey, ivspec);
            
            /*
             * Encrypt the private key and get bytes
             */
            
            _log.info("Encrypting private key");
            byte[] encryptedMsg = ecipher.doFinal(MsgToEncrypt.getBytes());
            
            /*
             * Encode encrypted the private key, salt, and vector
             */
            
            _log.info("Encoding encrypted private key, salt and vector");
            String encodeECCEncryptedPrivateKey = Base64.getEncoder().encodeToString(encryptedMsg);
            String encodeSalt = Base64.getEncoder().encodeToString(salt);
            String encodeVector = Base64.getEncoder().encodeToString(vector);
            
            _log.info("Encryption process completed");
            
            /*
             * Add data to database
             */
            
            _log.info("Insert key data to database");
            
            String sign_name = currentUser.getFullName();
			EntKey genkey = EntKeyLocalServiceUtil.createEntKey(currentUserId);
			genkey.setUserId(currentUserId);
			genkey.setSign_name(sign_name);
			genkey.setKey_dateCreated(currentDateTime);
			genkey.setPrivatekey_Data(encodeECCEncryptedPrivateKey);
			genkey.setPublickey_Data(encodedECCpublicKeyBytes);
			genkey.setSalt_Data(encodeSalt);
			genkey.setVector_Data(encodeVector);

			genkey = EntKeyLocalServiceUtil.addEntKey(genkey);
			
			_log.info("Key data added to database");
			_log.info("COMPLETED: Successfully generated key");
			SessionMessages.add(actionRequest, "request_processed", "Generated new keys");
			
        } catch (Exception e) {
            e.printStackTrace();
            _log.error("Error on generating key");
            SessionErrors.add(actionRequest, "error-key-keyExist");
			actionResponse.setRenderParameter("mvcPath", "/view.jsp");
        }
    }
	
	/*
	 * 
	 * Delete key method
	 * 
	 */
	
	public void doDelKey(User currentUser, ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		
		_log.info("###################################################");
		_log.info("#                  doDelKey log                   #");
		_log.info("###################################################");
		_log.info("START: Deleting Key Function");
		
		try {
			_log.info("Deleting key");
			EntKeyLocalServiceUtil.deleteEntKey(currentUser.getUserId());
			_log.info("COMPLETED: Successfully deleted key");
			SessionMessages.add(actionRequest, "request_processed", "Keys have been deleted.");
		} catch (PortalException | SystemException e) {
			_log.error("Error deleting key");
			SessionErrors.add(actionRequest, "error-key-keyNoExist");
			actionResponse.setRenderParameter("mvcPath", "/view.jsp");
			e.printStackTrace();
		}
	}
	
	
}