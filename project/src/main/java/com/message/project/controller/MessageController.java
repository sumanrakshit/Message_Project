package com.message.project.controller;

import com.message.project.entity.ErrorResponse;
import com.message.project.entity.Message;
import com.message.project.entity.MessageResponse;
import com.message.project.entity.MessagerRequest;
import com.message.project.entity.User;
import com.message.project.entity.UserResponse;
import com.message.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/listmessage")
    public ResponseEntity<?> messagefecthlist(@RequestParam(defaultValue = "10") int limit,
                                          @RequestParam(defaultValue = "-1") int next) {
        List<Message> messages = messageService.messagefecthlist(limit, next);
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setError("signature didn't match");
        if (limit > 20) {
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        if (next != -1) {
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/postmessage")
    public ResponseEntity<?> messagepostgetid(@RequestBody MessagerRequest messagerRequest)
    {
        //messageService.postMessage(messagerRequest);
        return  new ResponseEntity<>( messageService.messagepostgetid(messagerRequest),HttpStatus.CREATED);

    }
    
    
    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody User user)
    {
    	UserResponse userResponse=messageService.createUser(user);
    	return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
    	
    }
    
    @GetMapping("/getkey/{username}")
    public ResponseEntity<?> getKey(@PathVariable String username)
    {
    	System.out.println("Hello world");
    	String res=messageService.keygenerateusername(username);
    	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/getkey")
    public ResponseEntity<?> getpublickey(){
    	try {
			return new ResponseEntity<>(messageService.createpublicKey(),HttpStatus.OK);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
    }


}
