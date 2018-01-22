package genKey.portlet;

import genKey.constants.GenKeyPortletKeys;
import com._42Penguins.gosign.model.EntKey;
import com._42Penguins.gosign.service.EntDocLocalServiceUtil;
import com._42Penguins.gosign.service.EntFileUploadLocalServiceUtil;
import com._42Penguins.gosign.service.EntKeyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
	
	/**
	 * doAction method validate if the 6 pin digits is valid. If valid, proceed to doGenKey method
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 * @throws NoSuchAlgorithmException
	 */
	
	@ProcessAction(name = "doAction")
	public void doAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, NoSuchAlgorithmException {
		
    	ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		
		//==> Retrieve action
		String doAction = ParamUtil.getString(actionRequest, "doAction");
		String doGenKey = "genkey";
		String doDelKey = "delkey";
		
		if (doAction.equals(doGenKey)){
			
			//==> Retrieve user pin parameter
			String userPin = ParamUtil.getString(actionRequest, "userPin");

			//==> Standard length for user pin is 6 digits only
			int userPinLength = 6;
			
			if (userPin.length() == userPinLength) {
				doGenKey(currentUser, actionRequest, actionResponse);
			} else {
				SessionErrors.add(actionRequest, "error-key-invalidPinFormat");
			}
			
		} else if (doAction.equals(doDelKey)){
				doDelKey(currentUser, actionRequest, actionResponse);
				
		}	else{
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
        	 * Initialize to generate key ID & user ID
        	 */
	
    		//==> Get current date & time
    		ZoneId zoneIdMYS = ZoneId.of("Asia/Kuala_Lumpur");
    		LocalDateTime localDateTime = LocalDateTime.now(zoneIdMYS);
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    		String currentDateTime = localDateTime.format(formatter);
    		
    		//long keyId = CounterLocalServiceUtil.increment();
    		long currentUserId = currentUser.getUserId();
    		
    		System.out.println("\n========== START: Generate ECC Key ==========\n");
    		
            KeyPairGenerator kpg;
            kpg = KeyPairGenerator.getInstance("EC","SunEC");

            ECGenParameterSpec ecsp;
            ecsp = new ECGenParameterSpec("sect163k1");
            kpg.initialize(ecsp);

            KeyPair kp = kpg.genKeyPair();
            PrivateKey privKey = kp.getPrivate();
            PublicKey pubKey = kp.getPublic();
            //System.out.println("Raw private key: "+privKey.toString());
            //System.out.println("Raw public key"+pubKey.toString());
            
            //==> Get the key formats
            //String formatPrivate = privKey.getFormat(); // PKCS#8
            //String formatPublic = pubKey.getFormat(); // X.509  
            //System.out.println("Format of private key: "+formatPrivate);
            //System.out.println("Format of public key: "+formatPublic);
            
            byte[] privateKeyBytes = privKey.getEncoded();
            byte[] publicKeyBytes = pubKey.getEncoded(); 
    		
            String encodedECCprivateKeyBytes = Base64.getEncoder().encodeToString(privateKeyBytes);
            String encodedECCpublicKeyBytes = Base64.getEncoder().encodeToString(publicKeyBytes);
            //System.out.println("Generated ECC private key: "+encodedECCprivateKeyBytes);
            //System.out.println("Generated ECC public key: "+encodedECCpublicKeyBytes);
            
    		System.out.println("\n========== END: Generate ECC Key ==========\n");	
    		
            /*
             * Encryption of private key
             */
            
            System.out.println("\n===== START: Encrypting private key using AES 128 bit (6 digits) =====\n");
          
            //Generate salt
            Random r = new SecureRandom();
    		byte[] salt = new byte[32];
    		r.nextBytes(salt);
    		//System.out.println("salt: "+salt);
    		
    		//initialize iteration
    		int iteration = 200000;
    		System.out.println("Iteration: " + iteration);
    		
    		//initialize bits
    		int bit = 128;
    		System.out.println("Bit: " + 128);
    		
    		//initialize vector
    		byte[] vector = new byte[128/8];
    		r.nextBytes(vector);
    		IvParameterSpec ivspec = new IvParameterSpec(vector);
    		
    		//initialize variables
    		String MsgToEncrypt = encodedECCprivateKeyBytes;
            String userPin = ParamUtil.getString(actionRequest, "userPin");
            Cipher ecipher;
    		
            //Generating AES key
            SecretKeyFactory factory =  SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec mykeySpec = new PBEKeySpec(userPin.toCharArray(), salt, iteration, bit);
            SecretKey tmp = factory.generateSecret(mykeySpec);
            SecretKeySpec mySecretkey = new SecretKeySpec(tmp.getEncoded(), "AES");
            
            //==> Create and initiate encryption
            System.out.println("Initiate encryption alogrithm...");
            ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //System.out.println("Algorithm to encrypt private key: " + ecipher);
            ecipher.init(Cipher.ENCRYPT_MODE, mySecretkey, ivspec);
            //System.out.println("SecKey: "+mySecretkey);
            
            //==> Encrypt the private key and get bytes
            byte[] encryptedMsg = ecipher.doFinal(MsgToEncrypt.getBytes());
            //System.out.println("Encrypted PrivateKey: "+encryptedMsg);
            
            //==> Encode encrypted the private key
            String encodeECCEncryptedPrivateKey = Base64.getEncoder().encodeToString(encryptedMsg);
            //System.out.println("Encoded Encrypted PrivateKey: "+encodeECCEncryptedPrivateKey);
            
            String encodeSalt = Base64.getEncoder().encodeToString(salt);
            String encodeVector = Base64.getEncoder().encodeToString(vector);
            
            //System.out.println("encodeSalt: "+encodeSalt);
            //System.out.println("encodeVector: "+encodeVector);
            
            System.out.println("\n===== END: Encrypting private key using AES 128 bit (6 digits) =====\n");
            
            /*
             * Add data to database
             */
            
            String sign_name = currentUser.getFullName();
            
			System.out.println("Adding data to database...");
			EntKey genkey = EntKeyLocalServiceUtil.createEntKey(currentUserId);
			genkey.setUserId(currentUserId);
			genkey.setSign_name(sign_name);
			//genkey.setKey_version(keyVersion);
			genkey.setKey_dateCreated(currentDateTime);
			genkey.setPrivatekey_Data(encodeECCEncryptedPrivateKey);
			genkey.setPublickey_Data(encodedECCpublicKeyBytes);
			genkey.setSalt_Data(encodeSalt);
			genkey.setVector_Data(encodeVector);

			genkey = EntKeyLocalServiceUtil.addEntKey(genkey);

			System.out.println("Data added to database");
			System.out.println("======================== End ======================");     
			SessionMessages.add(actionRequest, "request_processed", "Generated new keys.");
			
        } catch (Exception e) {
            e.printStackTrace();
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
		try {
			EntKeyLocalServiceUtil.deleteEntKey(currentUser.getUserId());
			System.out.println("Key has been deleted");
			SessionMessages.add(actionRequest, "request_processed", "Keys have been deleted.");
		} catch (PortalException | SystemException e) {
			SessionErrors.add(actionRequest, "error-key-keyNoExist");
			actionResponse.setRenderParameter("mvcPath", "/view.jsp");
			e.printStackTrace();
		}
	}
	
	
}