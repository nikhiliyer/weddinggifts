package com.gift.occasion.db;

public class GiftDO {

	private Long id;
	private Long occasionId;
	private String name;
	private String description;
	private String imageUrl;
	private Integer price;

	public GiftDO() {

	}

	public GiftDO(String name, String description, String imageUrl, Integer price) {
		super();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long giftId) {
		this.id = giftId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getOccasionId() {
		return occasionId;
	}

	public void setOccasionId(Long occasionId) {
		this.occasionId = occasionId;
	}

}
