package com.gift.occasion.managers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.occasion.db.ContributionDAO;
import com.gift.occasion.db.OccasionDAO;

@Service
public class ContributionManager {

	@Autowired
	ContributionDAO contributionDAO;

	@Autowired
	OccasionDAO occasionDAO;

	public ContributionListVO getContributionsForOccasion(Long occasionId) {

		ContributionListVO contributions = new ContributionListVO(occasionId);
		contributions.setContributions(contributionDAO.findContributionsForOccasion(occasionId));
		return contributions;
	}

	public void addContribution() {
		
		// 1. Validate secret key
		
		// 2. Add to DB
		
		// 3. Send out email notification to contact
		
	}
	
	private Boolean isValidSecretKey(Long occasionId, String inputSecretKey) {

		String actualSecretKeyForOccasion = occasionDAO.getSecretKeyForOccasion(occasionId);
		return StringUtils.equalsIgnoreCase(inputSecretKey, actualSecretKeyForOccasion);
	}
	
	public void getContributedAmountForOccasion(Long occasionId) {
		
		
	}

}
