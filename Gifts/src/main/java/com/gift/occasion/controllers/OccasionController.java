package com.gift.occasion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gift.occasion.db.OccasionVO;
import com.gift.occasion.managers.OccasionManager;
import com.gift.occasion.managers.WishListVO;

/**
 * Handles requests for information related to a wedding
 */
@Controller
public class OccasionController {

	private static final Logger log = LoggerFactory.getLogger(OccasionController.class);

	@Autowired
	OccasionManager occasionManager;

	// http://localhost:8080/gift/occasionInfo.gift?occasionId=1
	@RequestMapping(value = "/occasionInfo.gift", method = RequestMethod.GET)
	public @ResponseBody OccasionVO occasionInfo(@RequestParam(value = "occasionId", required = true) Long occasionId) {

		log.info("Request for occasion info. Occasion Id " + occasionId);
		return occasionManager.getOccasionInfo(occasionId);
	}

	// http://localhost:8080/gift/wishList.gift?occasionId=1
	@RequestMapping(value = "/wishList.gift", method = RequestMethod.GET)
	public @ResponseBody WishListVO wishList(@RequestParam(value = "occasionId", required = true) Long occasionId) {

		log.info("Request wish list for occasion. Occasion Id: " + occasionId);
		return occasionManager.getWishListForOccasion(occasionId);
	}

}
