package com.gift.occasion.db;

public class ContributionsByInvolvedPersonVO {

	private String name;
	private Integer totalAmountPledged = 0;
	private Integer totalAmountCollected = 0;

	public ContributionsByInvolvedPersonVO() {
	}

	public String getName() {
		return name;
	}

	public Integer getTotalAmountCollected() {
		return totalAmountCollected;
	}

	public Integer getTotalAmountPledged() {
		return totalAmountPledged;
	}

	public void setName(String involvedPersonName) {
		this.name = involvedPersonName;
	}

	public void setTotalAmountPledged(Integer totalAmountPledged) {
		this.totalAmountPledged = totalAmountPledged;
	}

	public void setTotalAmountCollected(Integer totalAmountCollected) {
		this.totalAmountCollected = totalAmountCollected;
	}

}
