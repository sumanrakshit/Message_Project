package com.message.project.entity;

public class MessageResponse {
	private int  message_id;

//	public MessageResponse() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public MessageResponse(int message_id) {
//		super();
//		this.message_id = message_id;
//	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	@Override
	public String toString() {
		return "MessageResponse [message_id=" + message_id + "]";
	}
	
	
	

}
