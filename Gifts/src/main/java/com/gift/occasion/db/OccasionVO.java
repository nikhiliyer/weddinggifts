package com.gift.occasion.db;

import java.util.ArrayList;
import java.util.List;

public class OccasionVO extends OccasionDO {

	private TotalContributionsVO totalContributions;
	private List<EventDO> events;
	private List<ContactPersonDO> contacts;

	public OccasionVO(OccasionDO occasionDO) {

		this.setId(occasionDO.getId());
		this.setTitle(occasionDO.getTitle());
		this.setDescription(occasionDO.getDescription());
		this.setImageUrl(occasionDO.getImageUrl());
		this.setType(occasionDO.getType());
		this.events = new ArrayList<EventDO>();
		this.contacts = new ArrayList<ContactPersonDO>();
	}

	public List<EventDO> getEvents() {
		return events;
	}

	public List<ContactPersonDO> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactPersonDO> contacts) {
		this.contacts = contacts;
	}

	public void setEvents(List<EventDO> functions) {
		this.events = functions;
	}

	public TotalContributionsVO getTotalContributions() {
		return totalContributions;
	}

	public void setTotalContributions(TotalContributionsVO totalContributions) {
		this.totalContributions = totalContributions;
	}
}
