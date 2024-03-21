package com.message.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messageid;
	
	private String date;
	private String author;
	private String message;
	@Column(length = 95555)
	private String attachment;
	@Column(length = 65555)
	private String signature;
//	public int getMessage_id() {
//		return message_id;
//	}
//	public void setMessage_id(int message_id) {
//		this.message_id = message_id;
//	}
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
//	public Message(int message_id, String date, String author, String message, String attachment, String signature) {
//		super();
//		this.message_id = message_id;
//		this.date = date;
//		this.author = author;
//		this.message = message;
//		this.attachment = attachment;
//		this.signature = signature;
//	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public Message(int messageid, String date, String author, String message, String attachment, String signature) {
		super();
		this.messageid = messageid;
		this.date = date;
		this.author = author;
		this.message = message;
		this.attachment = attachment;
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "Message [messageid=" + messageid + ", date=" + date + ", author=" + author + ", message=" + message
				+ ", attachment=" + attachment + ", signature=" + signature + "]";
	}

	
	
	

}
