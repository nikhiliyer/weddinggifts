package com.gift.occasion.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.gift")
public class Config {
	
	@PostConstruct
	public void init() throws Exception{
	}

}
