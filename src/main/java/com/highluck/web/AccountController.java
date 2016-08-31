package com.highluck.web;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.CallableMethodReturnValueHandler;

import com.highluck.domain.Account;
import com.highluck.response.CommonResponse;
import com.highluck.service.AccountService;


@RestController
@RequestMapping("/accounts")
public class AccountController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String test(){
		
		return "testgoood";
	}
	
	@RequestMapping(value ="/{email}", method = RequestMethod.GET)
	public @ResponseBody Callable<Account> findByEmail(@ModelAttribute Account account){
			
		return () -> {	
			return accountService.findByEmail(account.getEmail());
		};
	}
	
	@RequestMapping(value ="/", method = RequestMethod.POST)
	public @ResponseBody Callable<CommonResponse> set(@RequestBody Account account){
		
		return () -> {
			return accountService.set(account);			
		};
	}
}
