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
				
				if (contribution.getContactPersonId() != null) {

					ContactPersonDO contactPerson = contactPersonDAO
							.findById(contribution.getContactPersonId());
					if (contactPerson != null) {

						emailService.sendEmail(this.createNewContributionEmail(
								contactPerson, contribution));
					}
				}
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
	
	private EmailVO createNewContributionEmail(ContactPersonDO contactPerson, ContributionDO contribution) {
		
		Template template = velocityEngine.getTemplate("email_templates/add_contribution.vm");
		VelocityContext vc = new VelocityContext();
		vc.put("contactName", contactPerson.getName());
		vc.put("contributorName", contribution.getName());
		vc.put("amount", contribution.getAmountPledged());
		StringWriter writer = new StringWriter();
		template.merge(vc, writer);
		String emailBody = writer.toString();
		return new EmailVO(contactPerson.getEmail(), "New contribution notification", emailBody);
	}
	
	private Boolean isValidSecretKey(String inputSecretKey) {

		return StringUtils.equalsIgnoreCase(inputSecretKey, SECRET_KEY);
	}
	
	public void getContributedAmountForOccasion(Long occasionId) {
		
		
	}

}
