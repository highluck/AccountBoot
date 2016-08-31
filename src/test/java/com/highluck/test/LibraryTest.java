package com.highluck.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.highluck.AccountServerApplication;
import com.highluck.library.EmailSender;
import com.highluck.library.MailMessage;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = {AccountServerApplicationTests.class, AccountServerApplication.class})
public class LibraryTest {

	@Test
	public void mailTest() {
		
		int i =0;
		
		MailMessage mail = new MailMessage();
		mail.setMessage(
				"<img src=" + "naver.com" + "width="+"100" + "height=" + "200>");
		
		mail.setReciver("yello1109@daum.net");
		mail.setSubject("asd");
		
		//mailSender.sendAuthMail(mail);
		
	}

}
