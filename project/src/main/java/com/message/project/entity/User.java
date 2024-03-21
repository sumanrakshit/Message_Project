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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	@Column(length = 65555)
	private String publickey;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPublickey() {
		return publickey;
	}
	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
//	public User(int id, String username, String publickey) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.publickey = publickey;
//	}
//	public User() {
//	
//		// TODO Auto-generated constructor stub
//	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", publickey=" + publickey + "]";
	}
	
	

}
