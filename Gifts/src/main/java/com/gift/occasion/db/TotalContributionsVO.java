package com.gift.occasion.db;

import java.util.List;

public class TotalContributionsVO {

	private Integer maxGiftPriceForOccasion;
	private List<ContributionsByInvolvedPersonVO> contributionsByInvolvedPerson;

	public TotalContributionsVO(List<ContributionsByInvolvedPersonVO> contributionsByInvolvedPersons) {
		this.contributionsByInvolvedPerson = contributionsByInvolvedPersons;
	}

	public Integer getTotalAmountPledged() {

		Integer totalAmountPledged = 0;
		for (ContributionsByInvolvedPersonVO contribution : contributionsByInvolvedPerson) {

			if (contribution.getTotalAmountPledged() != null) {
				
				totalAmountPledged += contribution.getTotalAmountPledged();
			}
		}
		return totalAmountPledged;
	}

	public Integer getTotalAmountCollected() {

		Integer totalAmountCollected = 0;
		for (ContributionsByInvolvedPersonVO contribution : contributionsByInvolvedPerson) {

			if (contribution.getTotalAmountCollected() != null) {
				
				totalAmountCollected += contribution.getTotalAmountCollected();
			}
		}
		return totalAmountCollected;
	}

	public List<ContributionsByInvolvedPersonVO> getContributionsByInvolvedPerson() {
		return contributionsByInvolvedPerson;
	}

	public Integer getMaxGiftPriceForOccasion() {
		return maxGiftPriceForOccasion;
	}

	public void setMaxGiftPriceForOccasion(Integer maxGiftPriceForOccasion) {
		this.maxGiftPriceForOccasion = maxGiftPriceForOccasion;
	}

}
