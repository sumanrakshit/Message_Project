package com.message.project.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.message.project.entity.Key;
import com.message.project.entity.Message;
import com.message.project.entity.MessageResponse;
import com.message.project.entity.MessagerRequest;
import com.message.project.entity.UserResponse;

@Repository
public interface MessageService {
	
	public Message createMessage(MessagerRequest messagerRequest);
	public MessageResponse postMessage(MessagerRequest messagerRequest);
	
	public List<Message> listMessage(int limit, int next);
	public UserResponse createUser(String username, String key);
//	public Key CreateKey();
	 public String createId(String id ) throws NoSuchAlgorithmException ;
	public String generateKey(String username);
	public String dateTime();
	public  String signMessage(String date, String author, String message, String attachment) throws NoSuchAlgorithmException ;
	
	public List<Message> allMessage(String startingId,int count, boolean saveAttachment);
	
	

}
