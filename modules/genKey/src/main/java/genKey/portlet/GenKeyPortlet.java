package genKey.portlet;

import genKey.constants.GenKeyPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
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
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
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
		
	@ProcessAction(name = "genKey")
	public void genKey(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		
		
        try {
            
    		long genkeyId = CounterLocalServiceUtil.increment();
    		long currentUserId = ParamUtil.getLong(actionRequest, "currentUserId");
    		
    		/*
    		 * 
    		 * Generate public and private key using RSA 2048 bit
    		 * 
    		 */
        	
        	System.out.println("===== Generating public & private key using RSA 2048 bit =====");
    		
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048, new SecureRandom());

            // Get a private & public key from the generated key pair.
            KeyPair keyPair = keyGen.genKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            
            System.out.println("Raw private key: "+ privateKey);
            System.out.println("Raw public key: "+ publicKey);
            
            // Get the bytes of the public and private keys
            byte[] privateKeyBytes = privateKey.getEncoded();
            byte[] publicKeyBytes = publicKey.getEncoded();
            
            System.out.println("Bytes of private key: "+ privateKeyBytes);
            System.out.println("Bytes of public key: "+ publicKeyBytes);
            
            // Get the key formats
            String formatPrivate = privateKey.getFormat(); // PKCS#8
            String formatPublic = publicKey.getFormat(); // X.509  
            System.out.println("Format of private key: "+formatPrivate);
            System.out.println("Format of public key: "+formatPublic);
            
            // Encode public and private key to String
            String EncodedprivateKeyBytes = Base64.getEncoder().encodeToString(privateKeyBytes);
            String EncodedpublicKeyBytes = Base64.getEncoder().encodeToString(publicKeyBytes);
            System.out.println("Encoded private key: "+EncodedprivateKeyBytes);
            System.out.println("Encoded public key: "+EncodedpublicKeyBytes);
            
            /*
             * 
             * Encrypt private key here
             * 
             */
            
            System.out.println("===== Encrypting private key using PBEWithMD5AndDES (6 digits) =====");
            
            final int iterationCount = 10;
            final byte[] salt = {(byte)0xB2,(byte)0x12,(byte)0xD5,(byte)0xB2,(byte)0x44,(byte)0x21,(byte)0xC3,(byte)0xC3};
            Cipher ecipher;
            //Cipher dcipher;   
            String MsgToEncrypt = EncodedprivateKeyBytes;
            String UserKey = "123456";
            
            PBEKeySpec keySpec = new PBEKeySpec(UserKey.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            
            ecipher = Cipher.getInstance(key.getAlgorithm());
            //dcipher = Cipher.getInstance(key.getAlgorithm());
            
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            byte[] encryptedMsg = ecipher.doFinal(MsgToEncrypt.getBytes());
            System.out.println("Encrypted PrivateKey: "+encryptedMsg);
            
            String EncodeEncryptedPrivateKey = Base64.getEncoder().encodeToString(encryptedMsg);
            System.out.println("Encoded Encrypted PrivateKey: "+EncodeEncryptedPrivateKey);
            
            //dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            //String decryptedMsg = new String(dcipher.doFinal(encryptedMsg));
            //System.out.println("Decrypted PrivateKey: "+decryptedMsg);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
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