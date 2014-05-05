package com.gift.occasion.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.occasion.db.ContactPersonDAO;
import com.gift.occasion.db.ContributionDAO;
import com.gift.occasion.db.EventDAO;
import com.gift.occasion.db.GiftDAO;
import com.gift.occasion.db.GiftDO;
import com.gift.occasion.db.InvolvedPersonDAO;
import com.gift.occasion.db.OccasionDAO;
import com.gift.occasion.db.OccasionDO;
import com.gift.occasion.db.OccasionVO;

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
	
	@Autowired
	ContributionDAO contributionDAO;
	
	@Autowired
	InvolvedPersonDAO involvedPersonDAO;

	public OccasionVO getOccasionInfo(Long occasionId) {

		OccasionDO occasionDO = occasionDAO.findById(occasionId);
		OccasionVO occasionVO = new OccasionVO(occasionDO);
		occasionVO.setEvents(eventDAO.findEventsForOccasion(occasionId));
		occasionVO.setContacts(contactPersonDAO.findContactsForOccasion(occasionId));
		occasionVO.setTotalContributions(contributionDAO.findTotalContributionForOccasion(occasionId));
		occasionVO.setInvolvedPersons(involvedPersonDAO.findPeopleInvolvedInOccasion(occasionId));
		return occasionVO;
	}

	public WishListVO getWishListForOccasion(Long occasionId) {

		WishListVO wishList = new WishListVO(occasionId);
		List<GiftDO> giftsForOccasion = giftDAO
				.findGiftsForOccasion(occasionId);
		wishList.addGiftsToWishList(giftsForOccasion);
		return wishList;
	}

}
