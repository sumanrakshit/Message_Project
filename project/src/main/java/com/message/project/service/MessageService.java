package com.message.project.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.message.project.entity.Message;
import com.message.project.entity.MessageResponse;
import com.message.project.entity.MessagerRequest;
import com.message.project.entity.User;
import com.message.project.entity.UserResponse;

@Repository
public interface MessageService {
	
	public String createMessage(MessagerRequest messagerRequest);
	public MessageResponse messagepostgetid(MessagerRequest messagerRequest);
	
	public List<Message> messagefecthlist(int limit, int next);
	public UserResponse createUser(User user);
//	public Key CreateKey();
	 public String createId(String id ) throws NoSuchAlgorithmException ;
	public String keygenerateusername(String username);
	public String dateTime();
	public  String signMessage(String date, String author, String message, String attachment) throws NoSuchAlgorithmException ;
	
	public List<Message> allMessage(String startingId,int count, boolean saveAttachment);
	
	public String createpublicKey( ) throws NoSuchAlgorithmException  ;

}
