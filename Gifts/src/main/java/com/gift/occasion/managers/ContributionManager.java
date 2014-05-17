package com.gift.occasion.managers;

import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gift.occasion.db.ContactPersonDAO;
import com.gift.occasion.db.ContactPersonDO;
import com.gift.occasion.db.ContributionDAO;
import com.gift.occasion.db.ContributionDO;
import com.gift.occasion.db.OccasionDAO;
import com.gift.util.EmailService;
import com.gift.util.EmailVO;

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
	
	@Autowired
	ContactPersonDAO contactPersonDAO;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	VelocityEngine velocityEngine;

	public ContributionListVO getContributionsForOccasion(Long occasionId) {

		ContributionListVO contributions = new ContributionListVO(occasionId);
		contributions.setContributions(contributionDAO.findContributionsForOccasion(occasionId));
		return contributions;
	}

	public Boolean addContribution(ContributionDO contribution, String secretKey) {

		Boolean addSuccess = false;
		// 1. Validate secret key
		if (this.isValidSecretKey(secretKey)) {

			try {

				// 2. Add to DB
				addSuccess = contributionDAO.addContribution(contribution);
			} catch (Exception e) {

				log.error("Exception adding contribution for occasion "
						+ contribution.getOccasionId(), e);
				return false;
			}
			// 3. Send out email notification to contact
			try {
				
				this.sendEmailToContacts(contribution);
			} catch (Exception e) {

				log.error(
						"Exception sending new contribution email for occasion "
								+ contribution.getOccasionId(), e);
			}
		} else {

			log.error("Cannot add contribution. Incorrect secret key.");
		}
		return addSuccess;
	}
	
	private void sendEmailToContacts(ContributionDO contribution) {
		
		ContactPersonDO selectedContactPerson = null;
		if (contribution.getContactPersonId() != null) {
			
			selectedContactPerson = contactPersonDAO.findById(contribution.getContactPersonId());
		}
		ContactPersonDO primaryContact = contactPersonDAO.findPrimaryContactForOccasion(contribution.getOccasionId());
		
		String emailBody;
		if (selectedContactPerson != null) {
			
			emailBody = this.generateContributionEmailBody(selectedContactPerson, contribution);
		} else {
			
			emailBody = this.generateContributionEmailBody(primaryContact, contribution);
		}
		
		StringBuffer sb = new StringBuffer(primaryContact.getEmail());
		
		if (selectedContactPerson != null && !primaryContact.getId().equals(selectedContactPerson.getId())) {
			
			sb.append(",").append(selectedContactPerson.getEmail());
		}
		emailService.sendEmail(new EmailVO(sb.toString(), "New contribution notification", emailBody));
	}
	
	private String generateContributionEmailBody(ContactPersonDO contactToEmail, ContributionDO contribution) {
		
		Template template = velocityEngine.getTemplate("email_templates/add_contribution.vm");
		VelocityContext vc = new VelocityContext();
		vc.put("contactName", contactToEmail.getName());
		vc.put("contributorName", contribution.getName());
		vc.put("amount", contribution.getAmountPledged());
		StringWriter writer = new StringWriter();
		template.merge(vc, writer);
		String emailBody = writer.toString();
		return emailBody;
	}
	
	private Boolean isValidSecretKey(String inputSecretKey) {

		// For now, we are saying that if not secret key is passed, it is valid. will have to tighten that up later
		return StringUtils.isBlank(inputSecretKey) || StringUtils.equalsIgnoreCase(inputSecretKey, SECRET_KEY);
	}

}
