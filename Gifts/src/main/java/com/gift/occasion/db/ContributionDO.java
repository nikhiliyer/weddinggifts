package com.gift.occasion.db;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//@JsonIgnoreProperties({"occasionId", "contactPersonId", "involvedPersonId"})
public class ContributionDO {

	private Long id;
	private Long occasionId;
	private Long involvedPersonId;
	private Long contactPersonId;
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String paymentMethod;
	private Integer amountPledged;
	private Integer amountCollected;
	private String customMessage;
	private ContactPersonDO contactPerson;

	public ContributionDO() {

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

	public void setOccasionId(Long occasionId) {
		this.occasionId = occasionId;
	}

	public Long getContactPersonId() {
		return contactPersonId;
	}

	public void setContactPersonId(Long contactPersonId) {
		this.contactPersonId = contactPersonId;
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

	public Integer getAmountCollected() {
		return amountCollected;
	}

	public void setAmountCollected(Integer amount) {
		this.amountCollected = amount;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public ContactPersonDO getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPersonDO contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Integer getAmountPledged() {
		return amountPledged;
	}

	public void setAmountPledged(Integer amountPledged) {
		this.amountPledged = amountPledged;
	}

	public Long getInvolvedPersonId() {
		return involvedPersonId;
	}

	public void setInvolvedPersonId(Long involvedPersonId) {
		this.involvedPersonId = involvedPersonId;
	}

}
