package com.message.project.entity;

public class MessageResponse {
	private String message_id;

	public MessageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageResponse(String message_id) {
		super();
		this.message_id = message_id;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	@Override
	public String toString() {
		return "MessageResponse [message_id=" + message_id + "]";
	}
	
	
	

}
