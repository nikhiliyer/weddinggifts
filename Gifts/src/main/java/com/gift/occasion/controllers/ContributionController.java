package com.gift.occasion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gift.occasion.db.ContributionDO;
import com.gift.occasion.managers.ContributionListVO;
import com.gift.occasion.managers.ContributionManager;

@Controller
public class ContributionController {

	private static final Logger log = LoggerFactory.getLogger(ContributionController.class);
	
	@Autowired
	ContributionManager contributionManager;

	// http://localhost:8080/gift/contributionList.gift?occasionId=1
	@RequestMapping(value = "/contributionList.gift", method = RequestMethod.GET)
	public @ResponseBody ContributionListVO contributionList(@RequestParam(value = "occasionId", required = true) Long occasionId) {

		log.info("Request contributions for occasion. Occasion Id: "+ occasionId);
		return contributionManager.getContributionsForOccasion(occasionId);
	}
	
	// http://localhost:8080/gift/addContribution.gift?occasionId=1
	@RequestMapping(value = "/addContribution.gift", method = RequestMethod.POST)
	public @ResponseBody ContributionListVO addContribution(@RequestBody ContributionDO contribution, @RequestHeader(value="secretKey") String secretKey ) {

		log.info("Adding contribution for occasion. Occasion Id: " + contribution.getOccasionId());
		contributionManager.addContribution();
		return null;
	}
}
