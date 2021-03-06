package com.highluck.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;





@Entity(name = "account")
@org.hibernate.annotations.DynamicUpdate
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long no;
	@Column(nullable = true)
	private long id;
	@Column
	private String email;	
	@Column
	private String password;
	@Column(name = "user_produce_date", nullable = true)
	private Timestamp userProduceDate;
	@Column(name = "user_level", nullable = true)
	private int userLevel;
	@Column(name = "token_key")
	private String tokenKey;
	@Column(name= "email_auth")
	private int emailAuth;
	
	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Timestamp getUserProduceDate() {
		return userProduceDate;
	}

	public void setUserProduceDate(Timestamp userProduceDate) {
		this.userProduceDate = userProduceDate;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	
	public String getTokenKey() {
		return tokenKey;
	}
	
	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}
	
	public int getEmailAuth() {
		return emailAuth;
	}

	public void setEmailAuth(int emailAuth) {
		this.emailAuth = emailAuth;
	}

	@Override
	public String toString() {
		return "[" + id + "] email = " + email;
	}
	
}
