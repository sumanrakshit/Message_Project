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
import java.util.ArrayList;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.project.dbUtils.DbConnection;
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
	

	private Connection connection;
	public MesssageServiceImpl() throws SQLException
	{
		connection=DbConnection.getConnection();
	}
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public MessageResponse postMessage(MessagerRequest messagerRequest) {
//		// TODO Auto-generated method stub
		//int id=messageRepository.findByMessageid(messagerRequest.getDate(), messagerRequest.getAuthor(), messagerRequest.getMessage(),messagerRequest.getAttachment(), messagerRequest.getSignature());
		MessageResponse messageResponse=new MessageResponse();
		//messageResponse.setMessage_id(id);
		
		//return  messageResponse;
		
		/* Decode Attachment */
//		String attachment=messagerRequest.getAttachment();
//       byte[] decodedAttachment = Base64.getDecoder().decode(attachment);       
//       String decodedString = new String(decodedAttachment, StandardCharsets.UTF_8);
        
		/*----------------change vivek-------------*/
		//String result=createMessage(messagerRequest);
//		String signature=null;
//		try {
//			signature = signMessage(dateTime(), messagerRequest.getAuthor(), messagerRequest.getMessage(), messagerRequest.getAttachment());
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Message mess=new Message();
//		 mess.setDate(dateTime());
//		 mess.setAuthor(messagerRequest.getAuthor());
//		 mess.setMessage(messagerRequest.getMessage());
//		 mess.setAttachment(messagerRequest.getAttachment());
//		 System.out.println(messagerRequest.getAttachment());
//		//mess.setSignature("ahjshsdghgsdgsd");
//		 mess.setSignature(signature);
//		Message msg=messageRepository.save(mess);
		System.out.println("-----------");
		String datetime=dateTime();
		//if(!result.equals(null)) {
			String sql = "SELECT messageid FROM message WHERE date =? AND author = ? AND message = ? AND attachment = ? AND signature = ?";
			System.out.println("----sbdh");
			
			 try
			 {
				 PreparedStatement statement=connection.prepareStatement(sql);
				 statement.setString(1, messagerRequest.getDate());
				 statement.setString(2, messagerRequest.getAuthor());
				 statement.setString(3, messagerRequest.getMessage());
				 statement.setString(4, messagerRequest.getAttachment());
				 statement.setString(5,messagerRequest.getSignature() );
				 
				 ResultSet resultSet = statement.executeQuery();
				 
				  while (resultSet.next()) {
		                // Retrieving and printing the message IDs
		                int messageId = resultSet.getInt("messageid");
		                System.out.println("Message ID: " + messageId);
//		                MessageResponse messageResponse =new MessageResponse();
		                messageResponse.setMessage_id(messageId);
		               
		            }
					
					
				  //return messageResponse;
			 }
			 catch(SQLException e)
			 {
				 e.printStackTrace();
				 messageResponse.setMessage_id(-1);
			 }

		//}
		return messageResponse;
		
		 		
		
//		return null;
	}

	@Override
	public List<Message> listMessage(int limit, int next) {
		// TODO Auto-generated method stub
		List<Message> messages=messageRepository.findAll();
		List<Message>result=new ArrayList<Message>();
		List<Message> messageresponse=new ArrayList<Message>();

		int count = 0;
		for (int i = messages.size() - 1; i >= 0 && count < limit; i--) {
			Message message = messages.get(i);
			if (message.getMessageid() <= next || next == -1) {
				messageresponse.add(message);
				count++;
			}
		}
		return messageresponse;
	}
	
	

	@Override
	public UserResponse createUser(User user) {
		// TODO Auto-generated method stub
		String RSA_PRIVATE_KEY =
	            "-----BEGIN PUBLIC  KEY-----\n" +
	            		user.getPublickey() +
	           
	            "...\n" +
	            "-----END PUBLIC KEY-----";
		
		User usr=new User();
		usr.setUsername(user.getUsername());
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
		System.out.println("hellll    "+username);
		String key=userRepository.findByUsername(username).getPublickey();
		return key;
	}

	@Override
	public String createMessage(MessagerRequest messagerRequest) {
		 try {
			 
			 Message mess=new Message();
			 mess.setDate(dateTime());
			 mess.setAuthor(messagerRequest.getAuthor());
			 mess.setMessage(messagerRequest.getMessage());
			 mess.setAttachment(messagerRequest.getAttachment());
			 System.out.println(messagerRequest.getAttachment());
			//mess.setSignature("ahjshsdghgsdgsd");
			 mess.setSignature(signMessage(dateTime(), messagerRequest.getAuthor(), messagerRequest.getMessage(), messagerRequest.getAttachment()));
			Message msg=messageRepository.save(mess);
			if(msg!=null){
				return "Successfully saved in database";
			}
			
			return null;
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error commmmmmmm");
			 e.printStackTrace();
//			ErrorResponse errorResponse=new ErrorResponse();
//			errorResponse.setError("Message not add ");
//			
			
		}
		 return null;
		 
		
		
		
	}
	
	 public String createId(String id ) throws NoSuchAlgorithmException  {
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
//	            saveKeysToFile(keyPair);
	            saveToIniFile("mb.ini", keyPair,id );
	            System.out.println("Keys generated and saved successfully.");
	            //return "Keys generated and saved successfully.";
	            
	            return privateKeyStr;
	        } catch ( IOException e) {
	            System.err.println("Error generating or saving keys: " + e.getMessage());
	            return null;
	        }
	    }
	 
//	  public void saveKeysToFile(KeyPair keyPair) throws IOException {
//	        // Save private key
//	        try (OutputStream privateKeyOut = new FileOutputStream("mb.ini")) {
//	            System.out.println(privateKeyOut);
//	        	privateKeyOut.write(keyPair.getPrivate().getEncoded());
//	        }
//
//	        // Save public key
//	        try (OutputStream publicKeyOut = new FileOutputStream("public.key")) {
//	            publicKeyOut.write(keyPair.getPublic().getEncoded());
//	        }
//	    }
	  
	  
	  private static void saveToIniFile(String fileName, KeyPair keyPair, String id) throws IOException {
	        Properties properties = new Properties();
	        properties.setProperty("id", id); // Replace "your_id_here" with the actual ID
	        properties.setProperty("privateKey", Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));

	        try (FileOutputStream fos = new FileOutputStream(fileName)) {
	            properties.store(fos, "Private key configuration");
	        }
	        // Save public key
	        try (OutputStream publicKeyOut = new FileOutputStream("public.key")) {
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
		            "-----BEGIN PUBLIC KEY-----\n" +
		            		createKey( )+
		           
		            "...\n" +
		            "-----END PUBLIC KEY-----";
		 String private_key=createprivateKey();
	        try {
	        	System.out.println("----date "+date+" "+author+" "+message);
	            // Concatenate message fields into JSON format
	            String jsonData = "{\"date\":\"" + date + "\",\"author\":\"" + author + "\",\"message\":\"" + message + "\",\"attachment\":\"" + attachment + "\"}";
	            System.out.println("json data are ---- "+jsonData);
	            // Remove whitespace for formatting
	            jsonData = jsonData.replaceAll("\\s+", "");
	            System.out.println("json data are  2 ---- "+jsonData);
	            // Calculate SHA-256 digest
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            System.out.println("klllll");
	            byte[] hashedBytes = digest.digest(jsonData.getBytes(StandardCharsets.UTF_8));
	            System.out.println("line 3");
	            System.out.println(RSA_PRIVATE_KEY);
	            System.out.println("line 3----------------");
	            
	            // Load RSA private key
	            byte[] keyBytes = Base64.getDecoder().decode(private_key);
	            System.out.println("Line 4");
	            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
	            System.out.println("Line 5");
	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	            System.out.println("Line 6");
	            RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(spec);
	            System.out.println("Line 7");
	            
	            // Sign the digest with RSA private key
	            Signature signature = Signature.getInstance("SHA256withRSA");
	            System.out.println("Line 8");
	            signature.initSign(privateKey);
	            System.out.println("Line 9");
	            signature.update(hashedBytes);
	            System.out.println("Line 10");
	            byte[] signatureBytes = signature.sign();
	            System.out.println("Error check");
	            // Encode signature bytes to base64 string
	            return Base64.getEncoder().encodeToString(signatureBytes);
	            
	        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | InvalidKeySpecException |IllegalArgumentException e) {
	            e.printStackTrace();
	            System.out.println("error comes");
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
	
	
	public String createKey( ) throws NoSuchAlgorithmException  {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		System.out.println(" public Key "+ keyPair.getPublic());
		System.out.println(" Private Key "+ keyPair.getPrivate());
		
		String publicKeyStr = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		String privateKeyStr = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
		

		// Save keys to file
//            saveKeysToFile(keyPair);
//            saveToIniFile("mb.ini", keyPair,id );
//		System.out.println("Keys generated and saved successfully.");
		
		
		return publicKeyStr;
    }

	public String createprivateKey( ) throws NoSuchAlgorithmException  {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		System.out.println(" public Key "+ keyPair.getPublic());
		System.out.println(" Private Key "+ keyPair.getPrivate());
		
		String publicKeyStr = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		String privateKeyStr = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
		

		// Save keys to file
//            saveKeysToFile(keyPair);
//            saveToIniFile("mb.ini", keyPair,id );
//		System.out.println("Keys generated and saved successfully.");
		
		
		return privateKeyStr;
    }
	public List<Message> allMessage(String startingId,int count, boolean saveAttch)
	{
		List<Message> messages=messageRepository.findAll();
		
		
		
		if(count<=messages.size())
		{
			if(startingId.equals("-1") )
			{
				reverseMessages(messages,saveAttch,count);
			}
			else
			{
				processMessages(messages, saveAttch, startingId,count);
			}
			
		}
		else
		{
			System.out.println("Please enter valid count ");
		}
		
		
		return messages;
	}
	
	 private void processMessages(List<Message> messages, boolean saveAttch, String startingId, int count) {
//	        JSONArray messages = new JSONArray(responseBody);
		 int number = Integer.parseInt(startingId);
		 int last=number+count;
	        
	        for (int i = number; i < last; i++) {
	            Message message = messages.get(i);
	            saveMessage(message, saveAttch);
	        }
	    }
	 private void reverseMessages(List<Message> messages, boolean saveAttch,int count) {
//	        JSONArray messages = new JSONArray(responseBody);
		 int number=messages.size()-count;
	        
	        for (int i = messages.size()-1; i >=number ; i--) {
	            Message message = messages.get(i);
	            saveMessage(message, saveAttch);
	        }
	    }
	
	 
	 
	 private void saveMessage(Message message, boolean saveAttch) {
	        int messageId = message.getMessageid();
	        String attachment = message.getAttachment();

	        // Save attachment if present
	        if (saveAttch==true) {
	            saveAttachment(messageId, attachment);
	        }

	        // Print message details (as per the specified format)
	        String author = message.getAuthor();
	        String date = message.getDate();
	        String messageText = message.getMessage();
	        System.out.println(messageId + ": " + date + " " + author + " says \"" + messageText + "\" " + "this message may go multiple lines\n"
	        		+ "that's okay. make sure to end with a quote. if there is an attachment\n"
	        		+ "put a\n"
	        		+ "emoji after the quote and a space.");
	    }
	 
	  private void saveAttachment(int messageId, String attachment) {
	        // Decode attachment from Base64 and save to file
	        byte[] attachmentData = Base64.getDecoder().decode(attachment);
	        String filename = "\'data\'"+messageId + ".out";
	        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
	            outputStream.write(attachmentData);
	            System.out.println("Attachment saved to file: " + filename);
	        } catch (IOException e) {
	            System.err.println("Error saving attachment to file: " + e.getMessage());
	        }
	    }

}
