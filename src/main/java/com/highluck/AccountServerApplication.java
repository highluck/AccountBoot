package com.highluck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.highluck.common.ThymeleafConfig;


@SpringBootApplication(exclude = ThymeleafConfig.class)
public class AccountServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServerApplication.class, args);
	}
}
