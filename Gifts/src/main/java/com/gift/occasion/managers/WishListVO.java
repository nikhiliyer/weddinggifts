package com.gift.occasion.managers;

import java.util.ArrayList;
import java.util.List;

import com.gift.occasion.db.GiftDO;

public class WishListVO {

	private Long occasionId;
	private List<GiftDO> gifts;

	public WishListVO(Long occasionId) {
		super();
		this.gifts = new ArrayList<GiftDO>();
		this.occasionId = occasionId;
	}

	public void addGiftsToWishList(List<GiftDO> gifts) {

		this.gifts.addAll(gifts);
	}

	public Long getOccasionId() {
		return occasionId;
	}

	public List<GiftDO> getGifts() {
		return gifts;
	}
}
