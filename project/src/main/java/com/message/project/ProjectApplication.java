package com.message.project;

import java.util.Arrays;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.*;
import java.security.NoSuchAlgorithmException;

import com.message.project.service.MessageService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
@SpringBootApplication
public class ProjectApplication implements CommandLineRunner{
	@Autowired
	private static MessageService messageService;
	
	public static void main(String[] args) {
		
		SpringApplication.run(ProjectApplication.class, args);
        
	}
	public String JSONSave(String author,String message,String attachment) {
		
		
		JsonObject jsonObject = new JsonObject();
		try {
			jsonObject.addProperty("date",messageService.dateTime());
	        jsonObject.addProperty("author", author);
	        jsonObject.addProperty("message", message);
	        jsonObject.addProperty("attachment", attachment);
	        jsonObject.addProperty("signature", messageService.signMessage(messageService.dateTime(), author, message, attachment));

	     

		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		// Convert JSON object to string
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonObject);

        
        
                // Write JSON string to a file
        try (FileWriter writer = new FileWriter("output.json")) {
            writer.write(jsonString);
            System.out.println("JSON file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Success";
	}

	 public static void writeToPDF(JSONObject jsonObject, String filePath) throws IOException {
	        PDDocument document = new PDDocument();
	        PDPage page = new PDPage();
	        document.addPage(page);

	        PDPageContentStream contentStream = new PDPageContentStream(document, page);
	        contentStream.beginText();
	        contentStream.setFont(PDType1Font.HELVETICA, 12);
	        contentStream.newLineAtOffset(50, 700);

	        // Write JSON object to PDF
	        contentStream.showText(jsonObject.toString());

	        contentStream.endText();
	        contentStream.close();

	        document.save(new FileOutputStream(new File(filePath)));
	        document.close();
	    }
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		 if (args.length == 0) {
	            System.out.println("Usage: spring-boot-cli <command> [arguments]");
	            return;
	        }

	        String command = args[0];
	        
	        
	        
	        
	        String[] arguments = Arrays.copyOfRange(args, 1, args.length);
	        switch (command) {
	            case "post":
	               //messageService.postMessage(null);
	                break;
	            case "list":
	            	
	            	 int startingId = -1;
	                 int count = 10;
	                 boolean saveAttachment = false;

	                 for (int i = 0; i < arguments.length; i++) {
	                     switch (arguments[i].toLowerCase()) {
	                         case "--starting":
	                             startingId = Integer.parseInt(arguments[++i]);
	                             break;
	                         case "--count":
	                             count = Integer.parseInt(arguments[++i]);
	                             break;
	                         case "--save-attachment":
	                             saveAttachment = true;
	                             break;
	                         default:
	                             System.out.println("Unknown argument: " + arguments[i]);
	                             return;
	                     }
	                 }
	                 //messageService.listMessage(startingId, count);
	                 break;
	            	
	            	
	            	
	            	
//	                listMessages(args);
//	                break;
	            case "createID":
	               ///messageService.createId() ;
	                break;
	            default:
	                System.out.println("Unknown command: " + command);
	        }
		
	}

	private void createId() {
		// TODO Auto-generated method stub
		
	}

	private void listMessages(String[] args) {
		// TODO Auto-generated method stub
		
	}

	private void postMessage(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
