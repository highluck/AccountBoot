package com.highluck.web;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.highluck.domain.Account;
import com.highluck.response.LoginResponse;
import com.highluck.service.AccountLogService;


@RestController
@RequestMapping("/accounts")
public class AccountLogController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccountLogService accountLogService; 
	
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public @ResponseBody Callable<LoginResponse> login(@RequestBody Account account){
		
		return () -> {
		     
			HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		        String ip = req.getHeader("X-FORWARDED-FOR");
		        if (ip == null)
		            ip = req.getRemoteAddr();
		        	System.out.println("ip : " + ip);
			return accountLogService.login(account, ip);
		};
	}
	
	@RequestMapping(value ="/logout", method = RequestMethod.POST)
	public @ResponseBody Callable<LoginResponse> logout(@RequestBody Account account){
		
		return () -> {
			return accountLogService.logout(account);
		};
	}	
}
