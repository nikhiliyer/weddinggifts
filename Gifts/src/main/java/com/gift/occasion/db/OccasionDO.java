package com.gift.occasion.db;

import java.util.ArrayList;
import java.util.List;

public class OccasionDO {

	public enum OccasionType {
		WEDDING, BIRTHDAY
	}

	private Long id;
	private String type;
	private String title;
	private String description;
	private String imageUrl;
	private String secretKey;
	private List<EventDO> events;
	private List<ContactPersonDO> contacts;

	public OccasionDO() {

		this.events = new ArrayList<EventDO>();
		this.contacts = new ArrayList<ContactPersonDO>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String info) {
		this.title = info;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String pictureUrl) {
		this.imageUrl = pictureUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EventDO> getEvents() {
		return events;
	}

	public List<ContactPersonDO> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactPersonDO> contacts) {
		this.contacts = contacts;
	}

	public void setEvents(List<EventDO> functions) {
		this.events = functions;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
