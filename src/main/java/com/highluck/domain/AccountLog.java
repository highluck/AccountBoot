package com.highluck.domain;

import java.time.LocalDateTime;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "account_log")
@org.hibernate.annotations.DynamicUpdate
public class AccountLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long no;
	@Column
	private String email;
	@Column(name = "login_date",nullable=true)
	private Timestamp loginDate;
	@Column(name = "logout_date",nullable=true)
	private Timestamp logoutDate;
	@Column
	private String ip;
	
	public long getNo() {
		return no;
	}
	
	public void setNo(long no) {
		this.no = no;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Timestamp getLoginDate() {
		return loginDate;
	}
	
	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}
	
	public Timestamp getLogoutDate() {
		return logoutDate;
	}
	
	public void setLogoutDate(Timestamp logoutDate) {
		this.logoutDate = logoutDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
