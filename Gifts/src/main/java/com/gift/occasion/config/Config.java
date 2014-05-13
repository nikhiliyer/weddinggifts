package com.gift.occasion.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.gift")
public class Config {
	
	@PostConstruct
	public void init() throws Exception{
	}
	
	@Bean
	public Session session() {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("nikhil.iyer@gmail.com", "moon11planet");
					}
				});
		return session;
	}
	
	@Bean
	public VelocityEngine velocityEngine() {
		
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
		ve.setProperty("runtime.log.logsystem.log4j.category", "velocity");
		ve.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
		ve.init();
		return ve;
	}

}
