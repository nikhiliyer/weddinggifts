package com.gift.occasion.managers;

import java.util.ArrayList;
import java.util.List;

import com.gift.occasion.db.ContributionDO;

public class ContributionListVO {

	private Long occasionId;
	private List<ContributionDO> contributions;

	public ContributionListVO(Long occasionId) {
		super();
		this.occasionId = occasionId;
		this.contributions = new ArrayList<ContributionDO>();
	}

	public List<ContributionDO> getContributions() {
		return contributions;
	}

	public void setContributions(List<ContributionDO> contributions) {
		this.contributions = contributions;
	}

	public Long getOccasionId() {
		return occasionId;
	}

}
