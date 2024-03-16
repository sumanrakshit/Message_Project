package com.message.project.service.impl;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.project.entity.ErrorResponse;
import com.message.project.entity.Key;
import com.message.project.entity.Message;
import com.message.project.entity.MessageResponse;
import com.message.project.entity.MessagerRequest;
import com.message.project.entity.User;
import com.message.project.entity.UserResponse;
import com.message.project.repository.MessageRepository;
import com.message.project.repository.UserRepository;
import com.message.project.service.MessageService;

@Service
public class MesssageServiceImpl implements MessageService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	 
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public MessageResponse postMessage(MessagerRequest messagerRequest) {
		// TODO Auto-generated method stub
		int id=messageRepository.findByMessageid(messagerRequest.getDate(), messagerRequest.getAuthor(), messagerRequest.getMessage(),messagerRequest.getAttachment(), messagerRequest.getSignature());
		MessageResponse messageResponse=new MessageResponse();
		messageResponse.setMessage_id(id);
		
		return  messageResponse;
	}

	@Override
	public Message listMessage(int limit, int next) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse createUser(String username, String key) {
		// TODO Auto-generated method stub
		String RSA_PRIVATE_KEY =
	            "-----BEGIN PRIVATE KEY-----\n" +
	            		key +
	           
	            "...\n" +
	            "-----END PRIVATE KEY-----";
		
		User usr=new User();
		usr.setUsername(username);
		usr.setPublickey(RSA_PRIVATE_KEY);
		userRepository.save(usr);
		
		String str="Welcome";
UserResponse userResponse=new UserResponse();
userResponse.setMessage(str);
		
		return  userResponse;
	}

//	@Override
//	public Key CreateKey() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String generateKey(String username) {
		String key=userRepository.findByPublickey(username).getPublickey();
		return key;
	}

	@Override
	public Message createMessage(MessagerRequest messagerRequest) {
		 try {
			 Message mess=new Message();
			 mess.setDate(dateTime());
			 mess.setAuthor(messagerRequest.getAuthor());
			 mess.setMessage(messagerRequest.getMessage());
			 mess.setAttachment(messagerRequest.getAttachment());
			mess.setSignature(signMessage(dateTime(), messagerRequest.getAuthor(), messagerRequest.getMessage(), messagerRequest.getAttachment()));
			Message msg=messageRepository.save(mess);
			return msg;
		 } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			ErrorResponse errorResponse=new ErrorResponse();
//			errorResponse.setError("Message not add ");
//			
			
		}
		 return null;
		 
		
		
		
	}
	
	 public String createId() throws NoSuchAlgorithmException  {
	        // Generate public-private key pair
	        try {
	            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	            keyPairGenerator.initialize(2048);
	            KeyPair keyPair = keyPairGenerator.generateKeyPair();
	            System.out.println(" public Key "+ keyPair.getPublic());
	            System.out.println(" Private Key "+ keyPair.getPrivate());
	            
	            String publicKeyStr = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
	            String privateKeyStr = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
	            

	            // Save keys to file
	            saveKeysToFile(keyPair);
	            System.out.println("Keys generated and saved successfully.");
	            
	            
	            return privateKeyStr;
	        } catch ( IOException e) {
	            System.err.println("Error generating or saving keys: " + e.getMessage());
	            return null;
	        }
	    }
	 
	  public void saveKeysToFile(KeyPair keyPair) throws IOException {
	        // Save private key
	        try (OutputStream privateKeyOut = new FileOutputStream("public.key")) {
	            System.out.println(privateKeyOut);
	        	privateKeyOut.write(keyPair.getPrivate().getEncoded());
	        }

	        // Save public key
	        try (OutputStream publicKeyOut = new FileOutputStream("mb.ini")) {
	            publicKeyOut.write(keyPair.getPublic().getEncoded());
	        }
	    }
	
	
//	public static String signMessage(String message, String privateKeyFile) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
//        // Remove all whitespace for formatting
//        message = message.replaceAll("\\s+", "");
//
//        // Calculate SHA-256 digest
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        byte[] messageDigest = digest.digest(message.getBytes(StandardCharsets.UTF_8));
//
//        // Load private key from file
//        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile));
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//
//        // Sign the message digest with RSA private key
//        Signature rsa = Signature.getInstance("SHA256withRSA");
//        rsa.initSign(privateKey);
//        rsa.update(messageDigest);
//        byte[] signatureBytes = rsa.sign();
//
//        // Encode signature to Base64
//        return Base64.getEncoder().encodeToString(signatureBytes);
//    }
//	
	
	
	
	 public  String signMessage(String date, String author, String message, String attachment) throws NoSuchAlgorithmException {
		 String RSA_PRIVATE_KEY =
		            "-----BEGIN PRIVATE KEY-----\n" +
		            		createId()+
		           
		            "...\n" +
		            "-----END PRIVATE KEY-----";
	        try {
	            // Concatenate message fields into JSON format
	            String jsonData = "{\"date\":\"" + date + "\",\"author\":\"" + author + "\",\"message\":\"" + message + "\",\"attachment\":\"" + attachment + "\"}";
	            
	            // Remove whitespace for formatting
	            jsonData = jsonData.replaceAll("\\s+", "");
	            
	            // Calculate SHA-256 digest
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] hashedBytes = digest.digest(jsonData.getBytes(StandardCharsets.UTF_8));
	            
	            // Load RSA private key
	            byte[] keyBytes = Base64.getDecoder().decode(RSA_PRIVATE_KEY);
	            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	            RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(spec);
	            
	            // Sign the digest with RSA private key
	            Signature signature = Signature.getInstance("SHA256withRSA");
	            signature.initSign(privateKey);
	            signature.update(hashedBytes);
	            byte[] signatureBytes = signature.sign();
	            
	            // Encode signature bytes to base64 string
	            return Base64.getEncoder().encodeToString(signatureBytes);
	            
	        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | InvalidKeySpecException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	public String dateTime()
	{
		LocalDateTime localDateTime = LocalDateTime.now();

        // Convert LocalDateTime to ZonedDateTime
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        // Define date time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX");

        // Format the ZonedDateTime object to a string
        String formattedDate = zonedDateTime.format(formatter);
        return formattedDate;
	}

//	@Override
//	public Key CreateKey() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

}
