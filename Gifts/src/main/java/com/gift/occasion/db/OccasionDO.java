package com.gift.occasion.db;


public class OccasionDO {

	public static final long DEFAULT_OCCASION_ID = 1L;
	public static final long DEFAULT_INVOLVED_PERSON_ID = 1L;
	
	public enum OccasionType {
		WEDDING, BIRTHDAY
	}

	private Long id;
	private String type;
	private String title;
	private String description;
	private String imageUrl;

	public OccasionDO() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
