package com.message.project.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import com.message.project.entity.Key;
import com.message.project.entity.Message;
import com.message.project.entity.MessageResponse;
import com.message.project.entity.MessagerRequest;
import com.message.project.entity.UserResponse;

@Component
public interface MessageService {
	
	public Message createMessage(MessagerRequest messagerRequest);
	public MessageResponse postMessage(MessagerRequest messagerRequest);
	
	public Message listMessage(int limit, int next);
	public UserResponse createUser(String username, String key);
//	public Key CreateKey();
	 public String createId() throws NoSuchAlgorithmException ;
	public String generateKey(String username);
	
	

}
