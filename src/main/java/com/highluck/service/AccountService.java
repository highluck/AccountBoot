package com.highluck.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.highluck.common.LibraryContainer;
import com.highluck.domain.Account;
import com.highluck.library.CutEncryption;
import com.highluck.library.MailMessage;
import com.highluck.repository.AccountRepository;
import com.highluck.response.CommonResponse;

@Service
public class AccountService {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public final static String MAIL_AUTH = "30cut 인증메일 입니다.";
	
	@Autowired
	private LibraryContainer library;
	@Autowired
	private AccountRepository accountRepository;
	
	public Account findByEmail(String email){
		return accountRepository.findByEmail(email);
	}
	
	@Transactional
	public CommonResponse set(Account account){
		CommonResponse response = new CommonResponse();
		
		if(null != accountRepository.findByEmail(account.getEmail())){
			response.setReason(response.MAIL_OVER);
			response.setResult(response.OVER);
		}
		else{
			
			if(account.getEmail() == null || account.getPassword() == null) {
				response.setResult(CommonResponse.FAIL);
				response.setReason(CommonResponse.FAIL);
			}
			else{
				try {						
					
					account.setId(accountRepository.count()+1);
					account.setPassword(library.getCutEncryption().getEncSHA256(account.getPassword()));
					account.setUserProduceDate(Timestamp.valueOf(LocalDateTime.now()));
					account.setTokenKey(library.getCutEncryption().getEncSHA256(account.getEmail() + library.getAuthCode()));
					account.setUserLevel(0);
					account.setEmailAuth(0);

					MailMessage mail = new MailMessage();
					mail.setReciver(account.getEmail());
					mail.setSubject(MAIL_AUTH);
					mail.setTokenKey(account.getTokenKey());
					
					library.getEmailSender().sendAuthMail(mail);
					accountRepository.save(account);
					
					response.setResult(CommonResponse.SUCCESS);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();					
					response.setResult(CommonResponse.FAIL);
					response.setReason(e.toString());
				}
			}
		}	
		return response;
	}
}
