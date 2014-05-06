package com.gift.occasion.db;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"secretKey"})
public class OccasionVO extends OccasionDO {

	private List<InvolvedPersonDO> involvedPersons;
	private TotalContributionsVO totalContributions;
	private List<EventDO> events;
	private List<ContactPersonDO> contacts;
	private List<String> someComments;

	public OccasionVO(OccasionDO occasionDO) {

		this.setId(occasionDO.getId());
		this.setTitle(occasionDO.getTitle());
		this.setDescription(occasionDO.getDescription());
		this.setImageUrl(occasionDO.getImageUrl());
		this.setType(occasionDO.getType());
		this.events = new ArrayList<EventDO>();
		this.contacts = new ArrayList<ContactPersonDO>();
		this.involvedPersons = new ArrayList<InvolvedPersonDO>();
		this.someComments = new ArrayList<String>();
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

	public List<InvolvedPersonDO> getInvolvedPersons() {
		return involvedPersons;
	}

	public void setInvolvedPersons(List<InvolvedPersonDO> involvedPersons) {
		this.involvedPersons = involvedPersons;
	}

	public List<String> getSomeComments() {
		return someComments;
	}

	public void setSomeComments(List<String> someComments) {
		this.someComments = someComments;
	}
}
