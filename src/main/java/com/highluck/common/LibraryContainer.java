package com.highluck.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.highluck.library.AuthCode;
import com.highluck.library.CutEncryption;
import com.highluck.library.EmailSender;

@Component("library")
public class LibraryContainer {
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private CutEncryption cutEncryption;
	@Autowired
	private AuthCode authCode;
	
	public CutEncryption getCutEncryption() {
		return cutEncryption;
	}
	public AuthCode getAuthCode() {
		return authCode;
	}
	public EmailSender getEmailSender() {
		return emailSender;
	}

	
}
