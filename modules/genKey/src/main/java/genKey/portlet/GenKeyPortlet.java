package genKey.portlet;

import genKey.constants.GenKeyPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import DocRegistration.model.Document;
import DocRegistration.service.DocumentLocalServiceUtil;

import DocRegistration.model.GenKey;
import DocRegistration.service.GenKeyLocalServiceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import java.util.Base64; 

import org.osgi.service.component.annotations.Component;

/**
 * @author razim
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
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();
		renderRequest.setAttribute("currentUserId", currentUser.getUserId());
		super.doView(renderRequest, renderResponse);
	}
	
	@ProcessAction(name = "addDoc")
	public void addDoc(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, NoSuchAlgorithmException {
		
		long genkeyId = CounterLocalServiceUtil.increment();
		long currentUserId = ParamUtil.getLong(actionRequest, "currentUserId");
		

		/*
		 * Function to upload
		 * 
		 *
	    
		UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		//Get the uploaded file name with extension
		String uploadedFileName = uploadRequest.getFileName("file");
		File file = uploadRequest.getFile("file");
		InputStream inputStream = new FileInputStream(file);
		// Below is the actual blob data
		OutputBlob blobData = new OutputBlob(inputStream, file.length());
		System.out.println("inputStream: " + inputStream);

		/*
		 * Function to add data to database
		 */
		
		//OutputBlob privatekey_blob = new OutputBlob(inputStream, length)

		try {

			System.out.println("Adding data to database...");
			GenKey genkey = GenKeyLocalServiceUtil.createGenKey(genkeyId);
			
			genkey.setUserId(currentUserId);
			genkey.setUserId(currentUserId);
			//genkey.setPrivatekey_File(privatekey_blob);
			//genkey.setPublickey_File(privatekey_blob);

			genkey = GenKeyLocalServiceUtil.addGenKey(genkey);


		} catch (SystemException e) {
			e.printStackTrace();

		}
	}
	
	
	

	
	@ProcessAction(name = "genSign")
	public void genSign(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, PortalException, NoSuchAlgorithmException, InvalidKeySpecException, 
			NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
			IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, SignatureException {
		 	
			System.out.println("\n===== START: Decrypting private key using AES 128 bit =====\n");
			
			//==> Retrieve some data from database for current user
			System.out.println("Retrieving data from database...");
			long userId = ParamUtil.getLong(actionRequest, "currentUserId");
			GenKey genkey = GenKeyLocalServiceUtil.getGenKey(userId);
			String encodedEncryptedPriKey = genkey.getPrivatekey_Data();
			String encodedSalt = genkey.getSalt_Data();
			String encodedVector = genkey.getVector_Data();
			String dataToSign = "signthis!";
			
			//==> Retrieve user pin parameter
			System.out.println("Retrieving pin from user...");
			String userPin = ParamUtil.getString(actionRequest, "userPin");
			Cipher dcipher;
			
			//==> Decode private key, salt, and vector
			System.out.println("Decoding encrypted private key...");
	        byte[] decodedEncryptedPriKey = Base64.getDecoder().decode(encodedEncryptedPriKey);
	        System.out.println("Decoding Salt...");
	        byte[] decodedSalt = Base64.getDecoder().decode(encodedSalt);
	        System.out.println("Decoding Vector...");
	        byte[] decodedVector = Base64.getDecoder().decode(encodedVector);
            
            //Generating AES key
            SecretKeyFactory factory =  SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec mykeySpec = new PBEKeySpec(userPin.toCharArray(), decodedSalt, 10000, 128);
            SecretKey tmp = factory.generateSecret(mykeySpec);
            SecretKeySpec mySecretkey = new SecretKeySpec(tmp.getEncoded(), "AES");
            IvParameterSpec vector = new IvParameterSpec(decodedVector);
            
            //==> Create and initiate decryption using AES key
            System.out.println("Initiate decryption alogrithm...");
            dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //System.out.println("Algorithm to decrypt private key: " + dcipher);
            dcipher.init(Cipher.DECRYPT_MODE, mySecretkey, vector);
            //System.out.println("Secret key: " + mySecretkey);
            
            //==> Decrypt private key and get String
            System.out.println("Decrypting private key...");
            String decodedDecryptedPriKey = new String(dcipher.doFinal(decodedEncryptedPriKey));
            //System.out.println("Decrypted PrivateKey: "+decodedDecryptedPriKey); 
            byte[] FinaldecodedDecryptedPriKey = Base64.getDecoder().decode(decodedDecryptedPriKey);
            
	   		System.out.println("=============================START - SIGNATURE TEST============================");
			 
	   		//==> Get raw private key for signing
	   		KeyFactory kf = KeyFactory.getInstance("EC"); // or "EC" or whatever
	   		PrivateKey rawPrivateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(FinaldecodedDecryptedPriKey));
	   		 
	   		//System.out.println("Raw Private Key: "+rawPrivateKey);
   		 
            System.out.println("Signing...");  
            Signature signature = Signature.getInstance("SHA1withECDSA");
            byte[] bytes = dataToSign.getBytes("UTF8");
            signature.initSign(rawPrivateKey);      
            signature.update(bytes);
            byte[] digitalSignature = signature.sign();
            String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);
            
            System.out.println("Sign completed! " + encodedSignature);
            
            System.out.println("=============================END - SIGNATURE TEST============================");
            
            
            /*
            //////////////////////////////////////////////////////////////////////
            byte[] decodedSignature = Base64.getDecoder().decode(encodedSignature);
           
            
            System.out.println("Verify Signature using Key A...");           
            signature.initVerify(rawPrivateKey);
            signature.update(bytes);                     
            System.out.println("Signature is correct? " +signature.verify(decodedSignature));
            
            //////////////////////////////////////////////////////////////////////
            
            System.out.println("Verify Signature using Key B...");                
            Signature signature2 = Signature.getInstance("SHA256withRSA");         
            signature2.initVerify(publicKey2);
            signature2.update(bytes);   
            System.out.println("Signature is correct? " +signature2.verify(decodedSignature));
            
            System.out.println("=============================END - SIGNATURE TEST============================");
            
            
            
            
            /*
            dcipher = Cipher.getInstance(key.getAlgorithm());
            PBEKeySpec keySpec2 = new PBEKeySpec(userPin.toCharArray());
            SecretKeyFactory keyFactory2 = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key2 = keyFactory2.generateSecret(keySpec2);
            AlgorithmParameterSpec paramSpec2 = new PBEParameterSpec(salt, iterationCount);
            
            
            dcipher.init(Cipher.DECRYPT_MODE, key2, paramSpec2);s
            String decryptedMsg = new String(dcipher.doFinal(encryptedMsg));
            System.out.println("Decrypted PrivateKey: "+decryptedMsg);
            
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
         

         
			//==> Decrypt decoded ecnrypted Private key using user's 6 digits
         System.out.println("Decrypting decoded encrypted private key...");
         PBEKeySpec keySpec = new PBEKeySpec(userPin.toCharArray());
         SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
         SecretKey key = keyFactory.generateSecret(keySpec);
         AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
         
         //==> Create and initiate decryption
         System.out.println("Initiate decryption alogrithm...");
         dcipher = Cipher.getInstance(key.getAlgorithm());
         dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
         
         //==> Decrypt the private key
         System.out.println("Decrypting...");
         String decodedDecryptedPriKey = new String(dcipher.doFinal(base64decodedEncryptedPriKey));
         System.out.println("Decrypted PrivateKey: "+ decodedDecryptedPriKey);
         
         //==> 2nd decode decrypted the private key
         byte[] FinaldecodedDecryptedPriKey = Base64.getDecoder().decode(decodedDecryptedPriKey);
         System.out.println("Final decoded decrypted private key: "+FinaldecodedDecryptedPriKey);
         
         System.out.println("\n===== END: Decrypting private key using PBEWithMD5AndDES (6 digits) =====\n");

		 System.out.println("=============================START - SIGNATURE TEST============================");
		 
		 KeyFactory kf = KeyFactory.getInstance("EC"); // or "EC" or whatever
		 PrivateKey rawPrivateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(FinaldecodedDecryptedPriKey));
		 
		 System.out.println("Raw Private Key: "+rawPrivateKey);
		 
         System.out.println("Signing Text with Private Key A...");
         
         Signature signature = Signature.getInstance("SHA1withECDSA");
         byte[] bytes = "MyMessage".getBytes("UTF8");
         signature.initSign(rawPrivateKey);      
         signature.update(bytes);
         byte[] digitalSignature = signature.sign();
         String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);
         
         System.out.println("Signed with Private Key A! " + encodedSignature);
         
         /*
         //////////////////////////////////////////////////////////////////////
         byte[] decodedSignature = Base64.getDecoder().decode(encodedSignature);
        
         
         System.out.println("Verify Signature using Key A...");           
         signature.initVerify(rawPrivateKey);
         signature.update(bytes);                     
         System.out.println("Signature is correct? " +signature.verify(decodedSignature));
         
         //////////////////////////////////////////////////////////////////////
         
         System.out.println("Verify Signature using Key B...");                
         Signature signature2 = Signature.getInstance("SHA256withRSA");         
         signature2.initVerify(publicKey2);
         signature2.update(bytes);   
         System.out.println("Signature is correct? " +signature2.verify(decodedSignature));
         
         System.out.println("=============================END - SIGNATURE TEST============================");
		*/
	}
	
	
	@ProcessAction(name = "genKey")
	public void genKey(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		
        try {
            
        	/*
        	 * 
        	 * Initialize to generate key ID & user ID
        	 * 
        	 */
        	
    		//long keyId = CounterLocalServiceUtil.increment();
    		long currentUserId = ParamUtil.getLong(actionRequest, "currentUserId");
    		
    		System.out.println("\n========== START: Generate ECC Key ==========\n");
    		
            KeyPairGenerator kpg;
            kpg = KeyPairGenerator.getInstance("EC","SunEC");

            ECGenParameterSpec ecsp;
            ecsp = new ECGenParameterSpec("sect163k1");
            kpg.initialize(ecsp);

            KeyPair kp = kpg.genKeyPair();
            PrivateKey privKey = kp.getPrivate();
            PublicKey pubKey = kp.getPublic();
            System.out.println("Raw private key: "+privKey.toString());
            System.out.println("Raw public key"+pubKey.toString());
            
            //==> Get the key formats
            String formatPrivate = privKey.getFormat(); // PKCS#8
            String formatPublic = pubKey.getFormat(); // X.509  
            System.out.println("Format of private key: "+formatPrivate);
            System.out.println("Format of public key: "+formatPublic);
            
            byte[] privateKeyBytes = privKey.getEncoded();
            byte[] publicKeyBytes = pubKey.getEncoded(); 
    		
            String encodedECCprivateKeyBytes = Base64.getEncoder().encodeToString(privateKeyBytes);
            String encodedECCpublicKeyBytes = Base64.getEncoder().encodeToString(publicKeyBytes);
            System.out.println("Generated ECC private key: "+encodedECCprivateKeyBytes);
            System.out.println("Generated ECC public key: "+encodedECCpublicKeyBytes);
            
    		System.out.println("\n========== END: Generate ECC Key ==========\n");
    		
    		//////////////////////////////////////////////
    		
    		
    		/*
    		 * 
    		 * Generate public and private key using RSA 2048 bit
    		 * 
    		 */
        	
        	//System.out.println("\n========== START: Generating public & private key ==========\n");
        	//System.out.println("");
        	
        	 /*X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(bobEncodedPubKey);
        	 KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        	 PublicKey bobPubKey = keyFactory.generatePublic(bobPubKeySpec);
        	 Signature sig = Signature.getInstance("DSA");
        	 sig.initVerify(bobPubKey);
        	 sig.update(data);
        	 sig.verify(signature);
        	*/
        	
        	/*
        	
        	String genKeyAlgo = "RSA";
        	int genKeyBit = 4096;
        	
        	System.out.println("Key algorithm: " + genKeyAlgo);
        	System.out.println("Key bit: " + genKeyBit);
    		
        	//KeyFactory keyFactory = KeyFactory.getInstance(genKeyAlgo);
        	//PrivateKey privKey=keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privKeyBits));    	
        	
        	KeyPairGenerator keyGen = KeyPairGenerator.getInstance(genKeyAlgo);
            keyGen.initialize(genKeyBit, new SecureRandom());

            //==> Get a private & public key from the generated key pair.
            KeyPair keyPair = keyGen.genKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            //System.out.println("Raw private key: "+ privateKey);
            //System.out.println("Raw public key: "+ publicKey);
            
            //==> Get the bytes of the public and private keys
            //byte[] privateKeyBytes = privateKey.getEncoded();
            //byte[] publicKeyBytes = publicKey.getEncoded(); 
            //System.out.println("Bytes of private key: "+ privateKeyBytes);
            //System.out.println("Bytes of public key: "+ publicKeyBytes);
            
            //==> Get the key formats
            //String formatPrivate = privateKey.getFormat(); // PKCS#8
            //String formatPublic = publicKey.getFormat(); // X.509  
            //System.out.println("Format of private key: "+formatPrivate);
            //System.out.println("Format of public key: "+formatPublic);
            
            //==> Encode public and private key to String
            //String EncodedprivateKeyBytes = Base64.getEncoder().encodeToString(privateKeyBytes);
            //String EncodedpublicKeyBytes = Base64.getEncoder().encodeToString(publicKeyBytes);
            //System.out.println("Encoded private key: "+EncodedprivateKeyBytes);
            //System.out.println("Encoded public key: "+EncodedpublicKeyBytes);
            
            //==> Get bytes + Encode in 1 line
            String EncodedprivateKeyBytes = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            String EncodedpublicKeyBytes = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            System.out.println("Generated private key: "+EncodedprivateKeyBytes);
            System.out.println("Generated public key: "+EncodedpublicKeyBytes);
            System.out.println("\n========== END: Generating public & private key ==========\n");
            
            /*
             * 
             * Encryption of private key
             * 
             */
            
            System.out.println("\n===== START: Encrypting private key using AES 128 bit (6 digits) =====\n");
          
            //Generate salt
            Random r = new SecureRandom();
    		byte[] salt = new byte[8];
    		r.nextBytes(salt);
    		//System.out.println("salt: "+salt);
    		
    		//initialize vector
    		byte[] vector = new byte[128/8];
    		r.nextBytes(vector);
    		IvParameterSpec ivspec = new IvParameterSpec(vector);
    		//System.out.println("iv: "+iv);
    		
    		//initialize variables
    		String MsgToEncrypt = encodedECCprivateKeyBytes;
            String userPin = ParamUtil.getString(actionRequest, "userPin");
            Cipher ecipher;
    		
            //Generating AES key
            SecretKeyFactory factory =  SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec mykeySpec = new PBEKeySpec(userPin.toCharArray(), salt, 10000, 128);
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
            System.out.println("Encrypted PrivateKey: "+encryptedMsg);
            
            //==> Encode encrypted the private key
            String encodeECCEncryptedPrivateKey = Base64.getEncoder().encodeToString(encryptedMsg);
            System.out.println("Encoded Encrypted PrivateKey: "+encodeECCEncryptedPrivateKey);
            
            String encodeSalt = Base64.getEncoder().encodeToString(salt);
            String encodeVector = Base64.getEncoder().encodeToString(vector);
            
            System.out.println("encodeSalt: "+encodeSalt);
            System.out.println("encodeVector: "+encodeVector);
            
            //System.out.println("Start encryption and encoding...");
            //==> Combine encryption and encoding process in 1 line
            //String EncodeECCEncryptedPrivateKey = Base64.getEncoder().encodeToString(ecipher.doFinal(MsgToEncrypt.getBytes()));
            //System.out.println("Encoded ECC Encrypted PrivateKey: "+EncodeECCEncryptedPrivateKey);
            //System.out.println("Private key successfully encrypted!");
            
            /*
            System.out.println("Retrieving User's pin...");
            
            final int iterationCount = 10;
            final byte[] salt = {(byte)0xB2,(byte)0x12,(byte)0xD5,(byte)0xB2,(byte)0x44,(byte)0x21,(byte)0xC3,(byte)0xC3};
            Cipher ecipher;
            //Cipher dcipher;
            String MsgToEncrypt = EncodedECCprivateKeyBytes;
            String userPin = ParamUtil.getString(actionRequest, "userPin");
            
            //==> Generate key using user's 6 digits
            System.out.println("Generating with user's pin...");
            PBEKeySpec keySpec = new PBEKeySpec(userPin.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            
            //==> Create and initiate encryption
            System.out.println("Initiate encryption alogrithm...");
            ecipher = Cipher.getInstance(key.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            
            //==> Encrypt the private key and get bytes
            //byte[] encryptedMsg = ecipher.doFinal(MsgToEncrypt.getBytes());
            //System.out.println("Encrypted PrivateKey: "+encryptedMsg);
            
            //==> Encode encrypted the private key
            //String EncodeEncryptedPrivateKey = Base64.getEncoder().encodeToString(encryptedMsg);
            //System.out.println("Encoded Encrypted PrivateKey: "+EncodeEncryptedPrivateKey);
            
            System.out.println("Start encryption and encoding...");
            //==> Combine encryption and encoding process in 1 line
            String EncodeECCEncryptedPrivateKey = Base64.getEncoder().encodeToString(ecipher.doFinal(MsgToEncrypt.getBytes()));
            System.out.println("Encoded ECC Encrypted PrivateKey: "+EncodeECCEncryptedPrivateKey);
            System.out.println("Private key successfully encrypted!");
            
            */
            
            System.out.println("\n===== END: Encrypting private key using AES 128 bit (6 digits) =====\n");
            
            
            
            //Generate salt
            //Random r2 = new SecureRandom();
    		//byte[] salt2 = new byte[8];
    		//r2.nextBytes(salt2);
    		
    		//initialize vector
    		//byte[] iv2 = new byte[128/8];
    		//r2.nextBytes(iv2);
            
            /*
            
    		IvParameterSpec ivspec2 = new IvParameterSpec(iv);
    		
    		
    		//initialize variables
            String userPin2 = "123456";
            Cipher dcipher;
            
            //Generating AES key
            SecretKeyFactory factory2 =  SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec mykeySpec2 = new PBEKeySpec(userPin2.toCharArray(), salt, 10000, 128);
            SecretKey tmp2 = factory2.generateSecret(mykeySpec2);
            SecretKeySpec mySecretkey2 = new SecretKeySpec(tmp2.getEncoded(), "AES");
            
            //==> Create and initiate decryption
            System.out.println("Initiate decryption alogrithm...");
            dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            System.out.println("Algorithm to decrypt private key: " + dcipher);
            dcipher.init(Cipher.DECRYPT_MODE, mySecretkey2, ivspec2);
            System.out.println("Secret key: " + mySecretkey2);
            
            //==> Encrypt the private key and get bytes
            String decryptedMsg = new String(dcipher.doFinal(encryptedMsg));
            System.out.println("Decrypted PrivateKey: "+decryptedMsg);
            
            /*
            dcipher = Cipher.getInstance(key.getAlgorithm());
            PBEKeySpec keySpec2 = new PBEKeySpec(userPin.toCharArray());
            SecretKeyFactory keyFactory2 = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key2 = keyFactory2.generateSecret(keySpec2);
            AlgorithmParameterSpec paramSpec2 = new PBEParameterSpec(salt, iterationCount);
            
            
            dcipher.init(Cipher.DECRYPT_MODE, key2, paramSpec2);
            String decryptedMsg = new String(dcipher.doFinal(encryptedMsg));
            System.out.println("Decrypted PrivateKey: "+decryptedMsg);
            
            //genKeyComplete(actionRequest, actionResponse);
            */
            
            
			System.out.println("Adding data to database...");
			GenKey genkey = GenKeyLocalServiceUtil.createGenKey(currentUserId);
			genkey.setUserId(currentUserId);
			//genkey.setKey_version(keyVersion);
			genkey.setKey_dateCreated("1");
			genkey.setPrivatekey_Data(encodeECCEncryptedPrivateKey);
			genkey.setPublickey_Data(encodedECCpublicKeyBytes);
			genkey.setSalt_Data(encodeSalt);
			genkey.setVector_Data(encodeVector);

			genkey = GenKeyLocalServiceUtil.addGenKey(genkey);

			System.out.println("Data added to database");
			System.out.println("======================== End ======================");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/*
	@ProcessAction(name = "test2")
	public void test2(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		System.out.println("Hello");
		
		test3(actionRequest, actionResponse)
		
	}
	
	@ProcessAction(name = "test3")
	public String test3(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
	
		String x = "x";
		return x;
		
	}*/
	
	
	@ProcessAction(name = "genKeyComplete")
	public void genKeyComplete(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		
		
        try {
            
    		//long genkeyId = CounterLocalServiceUtil.increment();
    		//long currentUserId = ParamUtil.getLong(actionRequest, "currentUserId");
        	
        	// Get instance and initialize a KeyPairGenerator object.
        	
        	//String plainText;
        	//PrivateKey privateKey;
            //KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            //generator.initialize(2048, new SecureRandom());
            //KeyPair pair = generator.generateKeyPair();
            //Signature privateSignature = Signature.getInstance("SHA256withRSA");
            //privateSignature.initSign(privateKey);
            //privateSignature.update(plainText.getBytes(UTF_8));

            //byte[] signature = privateSignature.sign();
            
    		///////////////////////////////////////////////////////////
        	
        	System.out.println("=============================START - GENERATE KEY============================");
        	
            System.out.println("Generating Key A...");
            
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            //SecureRandom random = SecureRandom.getInstance("SHA256withRSA");
            keyGen.initialize(2048, new SecureRandom());

            // Get a PrivateKey from the generated key pair.
            KeyPair keyPair = keyGen.genKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            
            System.out.println("Raw private key A: "+ privateKey);
            System.out.println("Raw public key A: "+ publicKey);
            
            // Get the bytes of the public and private keys
            byte[] privateKeyBytes = privateKey.getEncoded();
            byte[] publicKeyBytes = publicKey.getEncoded();
            
            System.out.println("Bytes private key A: "+ privateKeyBytes);
            System.out.println("Bytes public key A: "+ publicKeyBytes);
            
            // Get the formats of the encoded bytes
            String formatPrivate = privateKey.getFormat(); // PKCS#8
            String formatPublic = publicKey.getFormat(); // X.509
            
            System.out.println("Format private key A: "+formatPrivate);
            System.out.println("Format public key A: "+formatPublic);
            
            
            //////////////////////////////////////////////////////////////////////
            
            System.out.println("Generating Key B...");
            
    		KeyPairGenerator keyGen2 = KeyPairGenerator.getInstance("RSA");
            //SecureRandom random = SecureRandom.getInstance("SHA256withRSA");
            keyGen2.initialize(2048, new SecureRandom());

            // Get a PrivateKey from the generated key pair.
            KeyPair keyPair2 = keyGen2.genKeyPair();
            PrivateKey privateKey2 = keyPair2.getPrivate();
            PublicKey publicKey2 = keyPair2.getPublic();
            
            System.out.println("Raw private key B: "+ privateKey2);
            System.out.println("Raw public key B: "+ publicKey2);
            
            // Get the bytes of the public and private keys
            byte[] privateKeyBytes2 = privateKey2.getEncoded();
            byte[] publicKeyBytes2 = publicKey2.getEncoded();
            
            System.out.println("Bytes private key B: "+ privateKeyBytes2);
            System.out.println("Bytes public key B: "+ publicKeyBytes2);
            
            // Get the formats of the encoded bytes
            String formatPrivate2 = privateKey2.getFormat(); // PKCS#8
            String formatPublic2 = publicKey2.getFormat(); // X.509
            
            System.out.println("Format private key B: "+formatPrivate2);
            System.out.println("Format public key B: "+formatPublic2);
    		
    		
            System.out.println("=============================END - GENERATE KEY============================");
            
            ///////////////////////////////////////////////////////////
            
            System.out.println("=============================START - SIGNATURE TEST============================");
            
            System.out.println("Signing Text with Private Key A...");
            
            Signature signature = Signature.getInstance("SHA256withRSA");
            byte[] bytes = "This is MD5 text that will be sign".getBytes("UTF8");
            signature.initSign(privateKey);      
            signature.update(bytes);
            byte[] digitalSignature = signature.sign();
            String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);
            
            System.out.println("Signed with Private Key A!");
            
            //////////////////////////////////////////////////////////////////////
            byte[] decodedSignature = Base64.getDecoder().decode(encodedSignature);
            
            
            System.out.println("Verify Signature using Key A...");           
            signature.initVerify(publicKey);
            signature.update(bytes);                     
            System.out.println("Signature is correct? " +signature.verify(decodedSignature));
            
            //////////////////////////////////////////////////////////////////////
            
            System.out.println("Verify Signature using Key B...");                
            Signature signature2 = Signature.getInstance("SHA256withRSA");         
            signature2.initVerify(publicKey2);
            signature2.update(bytes);   
            System.out.println("Signature is correct? " +signature2.verify(decodedSignature));
            
            System.out.println("=============================END - SIGNATURE TEST============================");
            
            System.out.println("=============================START - ENCODE KEY============================");
            
            //////////////////////////////////////////////////////////////////////
            
            //System.out.println("File has been signed");
            
            //System.out.println("Singature:" + new Encoder().encode(digitalSignature));
           
            
            //String base64encodedString = Base64.getEncoder().encodeToString(digitalSignature);
            //System.out.println("Encoded signature: " +base64encodedString);
            
            String x = Base64.getEncoder().encodeToString(publicKeyBytes);
            System.out.println("Encoded public key: "+x);
            String y = Base64.getEncoder().encodeToString(privateKeyBytes);
            System.out.println("Encoded private key: "+y);
            
            
            /*
             * Encrypt private key here
             */
            
            
            
            
            //byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            //System.out.println(base64decodedBytes);
            
            
            //byte[] base64decodedString = Base64.getDecoder().decode(base64encodedString);
            //System.out.println("Encoded signature: " +base64decodedString);
            byte[] x1 = Base64.getDecoder().decode(x);
            System.out.println("Encoded public key: "+x1);
            byte[] y1 = Base64.getDecoder().decode(y);
            System.out.println("Encoded private key: "+y1);
            //byte[] base64decodedBytes = Base64.getDecoder()
            
            System.out.println("===========START - ENCRYPT PRIVATE KEY TEST (PBEKeySpec)==================");
            
            int iterationCount = 10;
            byte[] salt = {(byte)0xB2,(byte)0x12,(byte)0xD5,(byte)0xB2,(byte)0x44,(byte)0x21,(byte)0xC3,(byte)0xC3};
            Cipher ecipher;
            Cipher dcipher;
            
            String MsgToEncrypt = y;
            System.out.println("Message to encrypt: " + MsgToEncrypt);
            String UserPassword = "123456";
            System.out.println("User Key: " + UserPassword);
            String UserPasswordTest1 = "123456";
            System.out.println("Test Password A: " + UserPasswordTest1);
            String UserPasswordTest2 = "111111";
            System.out.println("Test Password B: " + UserPasswordTest2);
            
            //Key aesKey = new SecretKeySpec("1234567812345678".getBytes(),"AES");
            //Cipher cipher = Cipher.getInstance("AES");
            
            //cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            //byte[] encryptedMsg = cipher.doFinal(MsgToEncrypt.getBytes());
            //System.out.println("Encrypted Msg: "+encryptedMsg);
            
            //Key aesKey2 = new SecretKeySpec("1234567812345678".getBytes(),"AES");
            //cipher.init(Cipher.DECRYPT_MODE, aesKey2);
            //String decryptedMsg = new String(cipher.doFinal(encryptedMsg));
            //System.out.println("Decrypted Msg: " + decryptedMsg);
            
            PBEKeySpec keySpec = new PBEKeySpec(UserPassword.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            byte[] encryptedMsg = ecipher.doFinal(MsgToEncrypt.getBytes());
            System.out.println("Encrypted Msg: "+encryptedMsg);
            
            System.out.println("Decrypt using password: "+ UserPasswordTest1);
            
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            String decryptedMsg = new String(dcipher.doFinal(encryptedMsg));
            System.out.println("Decrypted Msg: "+decryptedMsg);
            
            
            /////////////////////////////////////////////////
            PBEKeySpec keySpec2 = new PBEKeySpec(UserPasswordTest2.toCharArray());
            SecretKeyFactory keyFactory2 = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key2 = keyFactory2.generateSecret(keySpec2);
            AlgorithmParameterSpec paramSpec2 = new PBEParameterSpec(salt, iterationCount);
            
            System.out.println("Decrypt using password: "+ UserPasswordTest2);
            
            dcipher.init(Cipher.DECRYPT_MODE, key2, paramSpec2);
            String decryptedMsg2 = new String(dcipher.doFinal(encryptedMsg));
            System.out.println("Decrypted Msg: "+decryptedMsg2);
            
            //System.out.println(keySpec);
            //System.out.println(key);
            //System.out.println(new String(key.getEncoded()));
            
            /*
            
            GenKey genkey = GenKeyLocalServiceUtil.createGenKey(genkeyId);
			
			genkey.setUserId(currentUserId);
			genkey.setUserId(currentUserId);
			//genkey.setPrivatekey_File(privatekey_blob);
			//genkey.setPublickey_File(x);

			genkey = GenKeyLocalServiceUtil.addGenKey(genkey);
            
            //Files.write(Paths.get("signature"), digitalSignature) ;
            //Files.write(Paths.get("publickey"), keyPair.getPublic().getEncoded());
            
            
            
            
            //System.out.println("File has been signed");
            
            /*

            // Get an instance of Signature object and initialize it.
            Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
            signature.initSign(privateKey);

            // Supply the data to be signed to the Signature object
            // using the update() method and generate the digital
            // signature.
            byte[] bytes = Files.readAllBytes(Paths.get("/content/README.txt"));
            signature.update(bytes);
            byte[] digitalSignature = signature.sign();

            // Save digital privatekey and the public key to a file. 
            Files.write(Paths.get("signature"), digitalSignature) ;
            Files.write(Paths.get("publickey"), keyPair.getPublic().getEncoded());
            
            
            System.out.println("File has been signed");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
}