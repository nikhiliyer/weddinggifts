package com.gift.occasion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gift.occasion.db.ContributionDO;
import com.gift.occasion.db.OccasionDO;
import com.gift.occasion.managers.ContributionListVO;
import com.gift.occasion.managers.ContributionManager;

@Controller
public class ContributionController {

	private static final Logger log = LoggerFactory.getLogger(ContributionController.class);
	
	@Autowired
	ContributionManager contributionManager;

	// http://localhost:8080/gift/contributionList.gift?occasionId=1
	@RequestMapping(value = "/contributionList.gift", method = RequestMethod.GET)
	public @ResponseBody ContributionListVO contributionList(@RequestParam(value = "occasionId", required = false) Long occasionId) {

		if(occasionId==null) {
			
			occasionId = OccasionDO.DEFAULT_OCCASION_ID;
		}
		log.info("Request contributions for occasion. Occasion Id: "+ occasionId);
		return contributionManager.getContributionsForOccasion(occasionId);
	}
	
	// http://localhost:8080/gift/addContribution.gift
	@RequestMapping(value = "/addContribution.gift", method = RequestMethod.POST)
	public @ResponseBody AddContributionResponse addContribution(@RequestBody ContributionDO contribution) {

		if (contribution.getOccasionId() == null) {

			contribution.setOccasionId(OccasionDO.DEFAULT_OCCASION_ID);
		}
		if (contribution.getInvolvedPersonId() == null) {

			contribution.setInvolvedPersonId(OccasionDO.DEFAULT_INVOLVED_PERSON_ID);
		}
		log.info("Adding contribution for occasion. Occasion Id: " + contribution.getOccasionId());
		Boolean success = contributionManager.addContribution(contribution, null);
		AddContributionResponse response;
		if (success) {

			response = new AddContributionResponse("Success", "Contribution successfully added");
		} else {

			response = new AddContributionResponse("Failure", "Failed to add contribution. Please check with Admin");
		}
		return response;
	}
	
	private class AddContributionResponse {
		
		private String status;
		private String message;
		
		public AddContributionResponse(String status, String message) {
			super();
			this.status = status;
			this.message = message;
		}

		public String getStatus() {
			return status;
		}

		public String getMessage() {
			return message;
		}
		
		
	}
}
