package com.gift.util;

public class EmailVO {

	private String fromAddr;
	private String toAddr;
	private String subject;
	private String body;

	public EmailVO(String toAddr, String subject, String body) {
		super();
		this.fromAddr = "no-reply@gifts.com";
		this.toAddr = toAddr;
		this.subject = subject;
		this.body = body;
	}

	public String getFromAddr() {
		return fromAddr;
	}

	public String getToAddr() {
		return toAddr;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

}
