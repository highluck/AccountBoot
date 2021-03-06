package com.highluck.web;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
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
import com.highluck.service.AuthService;


@Controller
@RequestMapping("/auth")
public class AuthController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AuthService authService;
	
	@RequestMapping(value ="/account", method = RequestMethod.POST)
	public Callable<CommonResponse> accountAuth(@ModelAttribute Account account){
		
		return () -> {
			return authService.accountAuth(account);			
		};
	}
	
	@RequestMapping(value ="/email", method = RequestMethod.POST)
	public Callable<String> mailAuth(@ModelAttribute Account account){
		
		return () -> {
			return authService.mailAuth(account) ? "redirect:https://30cut.com/":"error";			
		};
	}
	
	@RequestMapping(value ="/email/resend", method = RequestMethod.POST)
	public @ResponseBody Callable<CommonResponse> mailAuthSend(@RequestBody Account account){
		
		return () -> {
			return authService.mailAuthSend(account);			
		};
	}
	
	@RequestMapping(value ="/password", method = RequestMethod.POST)
	public @ResponseBody Callable<CommonResponse> mailPassword(@RequestBody Account account){
		
		return () -> {
			return authService.mailPassword(account);			
		};
	}
	
	@RequestMapping(value ="/password/change", method = RequestMethod.POST)
	public Callable<String> mailConfirm(@ModelAttribute Account account){
		
		return () -> {
			if(!authService.passwordRegular(account.getPassword())){
				CommonResponse response = new CommonResponse();
				response.setReason("패스워드 형식이 틀립니다.");
				response.setResult("fail");
				System.out.println("saddd");
				return "passwordError";
			}
			else{
				return authService.changePassword(account) ? "redirect:https://30cut.com/" : "error";
			}
		};
	}
}
