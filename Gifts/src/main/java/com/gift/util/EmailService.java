package com.gift.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static final Logger log = LoggerFactory
			.getLogger(EmailService.class);

	@Autowired
	Session session;

	private ExecutorService executorService;

	public EmailService() {

		executorService = Executors.newCachedThreadPool();
	}

	public void sendEmail(EmailVO email) {

		try {

			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email.getFromAddr()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email.getToAddr()));
			message.setSubject(email.getSubject());
			message.setText(email.getBody());

			PendingMail pendingMail = new PendingMail(message);
			executorService.submit(pendingMail);
			log.info("Email sent successfully to " + email.getToAddr());
		} catch (Exception e) {

			log.error("Exception sending email", e);
		}
	}

	private class PendingMail implements Callable {

		Message message;

		protected PendingMail(Message message) {
			this.message = message;
		}

		public Object call() throws Exception {

			Transport.send(message);
			return null;
		}
	}

}
