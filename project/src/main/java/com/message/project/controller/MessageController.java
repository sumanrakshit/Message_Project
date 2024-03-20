package com.message.project.controller;

import com.message.project.entity.ErrorResponse;
import com.message.project.entity.Message;
import com.message.project.entity.MessageResponse;
import com.message.project.entity.MessagerRequest;
import com.message.project.entity.UserResponse;
import com.message.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/listmessage")
    public ResponseEntity<?> listMessages(@RequestParam(defaultValue = "10") int limit,
                                          @RequestParam(defaultValue = "-1") int next) {
        List<Message> messages = messageService.listMessage(limit, next);
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

    @PostMapping("/postmessage")
    public ResponseEntity<?> postMessage(@RequestBody MessagerRequest messagerRequest)
    {
        messageService.postMessage(messagerRequest);
        return  new ResponseEntity<>( messageService.postMessage(messagerRequest),HttpStatus.CREATED);

    }
    
    
    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestParam String username, @RequestParam String key)
    {
    	UserResponse userResponse=messageService.createUser(username, key);
    	return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
    	
    }
    
    @GetMapping("/getkey")
    public ResponseEntity<?> getKey(@PathVariable String username)
    {
    	String res=messageService.generateKey(username);
    	return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }


}
