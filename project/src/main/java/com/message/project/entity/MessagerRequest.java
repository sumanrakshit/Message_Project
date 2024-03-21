package com.message.project.entity;

public class MessagerRequest {
	
	private String date;
	private String author;
	private String message;
	private String attachment;
	private String signature;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
//	public MessagerRequest(String date, String author, String message, String attachment, String signature) {
//		super();
//		this.date = date;
//		this.author = author;
//		this.message = message;
//		this.attachment = attachment;
//		this.signature = signature;
//	}
//	public MessagerRequest() {
//
//		// TODO Auto-generated constructor stub
//	}
	

}
