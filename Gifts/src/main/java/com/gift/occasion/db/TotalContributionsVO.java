package com.gift.occasion.db;

public class TotalContributionsVO {
	
	private Integer totalAmountPledged;
	private Integer totalAmountCollected;
	
	public TotalContributionsVO() {
				
	}

	public Integer getTotalAmountPledged() {
		return totalAmountPledged;
	}

	public void setTotalAmountPledged(Integer amountPledged) {
		this.totalAmountPledged = amountPledged;
	}

	public Integer getTotalAmountCollected() {
		return totalAmountCollected;
	}

	public void setTotalAmountCollected(Integer amountCollected) {
		this.totalAmountCollected = amountCollected;
	}
	

}
