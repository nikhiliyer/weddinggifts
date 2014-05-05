package com.gift.occasion.db;

public class EventDO {

	private Long id;
	private Long occasionId;
	private String name;
	private String location;
	private String date;
	private String time;
	
	public EventDO(){
		
	}

	public EventDO(String name, String location, String date, String time) {
		super();
		this.name = name;
		this.location = location;
		this.date = date;
		this.time = time;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

}
