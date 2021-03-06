package com.highluck.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.hibernate.validator.constraints.EAN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highluck.common.LibraryContainer;
import com.highluck.domain.Account;
import com.highluck.domain.AccountLog;
import com.highluck.library.MailMessage;
import com.highluck.repository.AccountRepository;
import com.highluck.response.CommonResponse;
import com.highluck.response.LoginResponse;

@Service
public class AuthService {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final static String MAIL_PASSWORD = "패스워드 변경 메일 입니다.";
	private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$";
	
	@Autowired
	private LibraryContainer library;
	@Autowired
	private AccountRepository accountRepository;
	
	
	public CommonResponse accountAuth(Account request){
		
		CommonResponse response = new CommonResponse();
		Account account = accountRepository.findByEmail(request.getEmail());

		if(account.getTokenKey().equals(request.getTokenKey())){
			
			response.setResult(account.getEmailAuth()==1 ? response.SUCCESS : response.HALF);
		}
		else{
			response.setResult(response.FAIL);
			response.setReason(response.TOKEN_ERROR);
		}	
		return response;
	}
	
	
	@Transactional
	public boolean mailAuth(Account request){
		
		Account account = accountRepository.findByEmail(request.getEmail());
		CommonResponse response = new CommonResponse();
		
		if(account.getTokenKey().equals(request.getTokenKey())){
			account.setEmailAuth(1);
			return true;
		}
		else return false;
	}
	
	@Transactional
	public CommonResponse mailAuthSend(Account request) throws Exception{
		
		CommonResponse response = new CommonResponse();
		Account account = accountRepository.findByEmail(request.getEmail());
		
		if(account.getEmailAuth() == 0){
			account.setTokenKey(library.getCutEncryption().getEncSHA256(account.getEmail() + library.getAuthCode()));
			
			MailMessage mail = new MailMessage();
			mail.setReciver(request.getEmail());
			mail.setTokenKey(account.getTokenKey());
			mail.setSubject(AccountService.MAIL_AUTH);
			
			library.getEmailSender().sendAuthMail(mail);
			response.setResult(response.SUCCESS);
		}
		else{
			response.setResult(response.FAIL);
			response.setReason(response.MAIL_AUTH_ERROR);
		}
		
		return response;
	}
	
	@Transactional
	public CommonResponse mailPassword(Account request){
		CommonResponse response = new CommonResponse();
		Account account;
		try {
			
			account = accountRepository.findByEmail(request.getEmail());
			account.setTokenKey(library.getCutEncryption().getEncSHA256(account.getEmail() + library.getAuthCode()));
			
			MailMessage mail = new MailMessage();
			mail.setReciver(request.getEmail());
			mail.setTokenKey(account.getTokenKey());
			mail.setSubject(MAIL_PASSWORD);
			
			library.getEmailSender().sendPasswordMail(mail);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setReason(e.toString());
			response.setResult(response.FAIL);
			return response;
		}
		
		response.setResult(response.SUCCESS);
		
		return response;
	}
	
	@Transactional
	public boolean changePassword(Account request) throws Exception{
		
		CommonResponse response = new CommonResponse();
		Account account = accountRepository.findByEmail(request.getEmail());
	
		if(account.getTokenKey().equals(request.getTokenKey())){
			account.setPassword(library.getCutEncryption().getEncSHA256(request.getPassword()));
			account.setTokenKey("");
			return true;
		}
		else return false;
	}
	
	
	
	public boolean passwordRegular(String password){
	
		return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
	}
	
}
