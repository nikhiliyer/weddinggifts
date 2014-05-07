package com.gift.occasion.managers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.occasion.db.ContributionDAO;
import com.gift.occasion.db.ContributionDO;
import com.gift.occasion.db.OccasionDAO;

@Service
public class ContributionManager {
	
	private static final Logger log = LoggerFactory.getLogger(ContributionManager.class);
	
	// TODO: Move to properties
	// MD5 hash of HANK
	private static final String SECRET_KEY = "c9bfc4498a73674a3931771d7b1fad85";

	@Autowired
	ContributionDAO contributionDAO;

	@Autowired
	OccasionDAO occasionDAO;

	public ContributionListVO getContributionsForOccasion(Long occasionId) {

		ContributionListVO contributions = new ContributionListVO(occasionId);
		contributions.setContributions(contributionDAO.findContributionsForOccasion(occasionId));
		return contributions;
	}

	public Boolean addContribution(ContributionDO contribution, String secretKey) {
		
		// 1. Validate secret key
		if(this.isValidSecretKey(secretKey)) {
		
			try {
				
				// 2. Add to DB
				return contributionDAO.addContribution(contribution);
			} catch (Exception e) {

				log.error("Exception adding contribution for occasion "
						+ contribution.getOccasionId(), e);
				return false;
			}
			// 3. Send out email notification to contact
			
		} else {
			
			log.error("Cannot add contribution. Incorrect secret key.");
			return false;
		}
		
	}
	
	private Boolean isValidSecretKey(String inputSecretKey) {

		return StringUtils.equalsIgnoreCase(inputSecretKey, SECRET_KEY);
	}
	
	public void getContributedAmountForOccasion(Long occasionId) {
		
		
	}

}
