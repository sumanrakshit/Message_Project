package com.message.project.service;

import org.springframework.stereotype.Component;

import com.message.project.entity.Key;
import com.message.project.entity.Message;
import com.message.project.entity.MessagerRequest;

@Component
public interface MessageService {
	
	public Message createMessage(MessagerRequest messagerRequest);
	public int postMessage(MessagerRequest messagerRequest);
	
	public Message listMessage(int limit, int next);
	public String createUser(String username, String key);
	public Key CreateKey();
	public String generateKey(String username, String key);
	
	

}
