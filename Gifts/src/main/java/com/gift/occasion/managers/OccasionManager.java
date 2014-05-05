package com.gift.occasion.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.occasion.db.ContactPersonDAO;
import com.gift.occasion.db.EventDAO;
import com.gift.occasion.db.GiftDAO;
import com.gift.occasion.db.GiftDO;
import com.gift.occasion.db.OccasionDAO;
import com.gift.occasion.db.OccasionDO;

@Service
public class OccasionManager {

	@Autowired
	OccasionDAO occasionDAO;

	@Autowired
	GiftDAO giftDAO;

	@Autowired
	EventDAO eventDAO;

	@Autowired
	ContactPersonDAO contactPersonDAO;

	public OccasionDO getOccasionInfo(Long occasionId) {

		OccasionDO occasion = occasionDAO.findById(occasionId);
		occasion.setEvents(eventDAO.findEventsForOccasion(occasionId));
		occasion.setContacts(contactPersonDAO
				.findContactsForOccasion(occasionId));
		return occasion;
	}

	public WishListVO getWishListForOccasion(Long occasionId) {

		WishListVO wishList = new WishListVO(occasionId);
		List<GiftDO> giftsForOccasion = giftDAO
				.findGiftsForOccasion(occasionId);
		wishList.addGiftsToWishList(giftsForOccasion);
		return wishList;
	}

}
