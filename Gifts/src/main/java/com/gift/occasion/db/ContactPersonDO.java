package com.gift.occasion.db;

public class ContactPersonDO {

	private Long id;
	private Long occasionId;
	private String name;
	private String phoneNumber;
	private String email;
	private String address;

	public ContactPersonDO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOccasionId() {
		return occasionId;
	}

	public void setOccasionId(Long occasionID) {
		this.occasionId = occasionID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
