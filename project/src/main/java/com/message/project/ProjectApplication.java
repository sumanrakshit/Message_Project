package com.message.project;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.message.project.service.MessageService;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner{
	@Autowired
	private MessageService messageService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
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
	               messageService.postMessage(null);
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
	                 messageService.listMessage(startingId, count);
	                 break;
	            	
	            	
	            	
	            	
//	                listMessages(args);
//	                break;
	            case "createID":
	               messageService.createId() ;
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
