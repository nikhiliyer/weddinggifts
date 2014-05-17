package com.gift.occasion.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gift.dal.ConnectionProvider;

@Repository
public class ContributionDAO {
	
	
	private static final String GET_CUSTOM_MESSAGES_SQL = "select name, custom_message from contribution where occasion_id = ? order by created_on desc limit 3";
	private static final String TOTAL_CONTRIBUTION_SQL = "select name, total_amount_pledged, total_amount_collected from (select sum(amount_pledged) as total_amount_pledged, sum(amount_collected) as total_amount_collected, involved_person_id from contribution where occasion_id = ? group by involved_person_id) a, involved_person p where a.involved_person_id = p.id";
	private static final String INSERT_CONTRIBUTION_SQL = "insert into contribution (occasion_id, contact_person_id, involved_person_id, name, phone_number, email, address, custom_message, payment_method, amount_pledged) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	@Autowired
	ConnectionProvider connectionProvider;

	@Autowired
	ContactPersonDAO contactPersonDAO;
	
	@Autowired
	GiftDAO giftDAO;

	// TODO: Create DAL framework.

	public List<ContributionDO> findContributionsForOccasion(Long occasionId) {

		List<ContributionDO> contributions = connectionProvider
				.getJdbcTemplate().query(
						"select * from contribution where occasion_id = ? order by name",
						new Object[] { occasionId },
						new BeanPropertyRowMapper(ContributionDO.class));

		for (ContributionDO contributionDO : contributions) {

			contributionDO.setContactPerson(contactPersonDAO.findById(contributionDO.getContactPersonId()));
		}
		return contributions;
	}

	public TotalContributionsVO findTotalContributionForOccasion(Long occasionId) {

		List<ContributionsByInvolvedPersonVO> contributionsByInvolvedPerson = connectionProvider
				.getJdbcTemplate().query(
						TOTAL_CONTRIBUTION_SQL,
						new Object[] { occasionId },
						new BeanPropertyRowMapper(
								ContributionsByInvolvedPersonVO.class));
		TotalContributionsVO totalContributions = new TotalContributionsVO(
				contributionsByInvolvedPerson);
		totalContributions.setMaxGiftPriceForOccasion(giftDAO.findMaxGiftAmountForOccasion(occasionId));
		return totalContributions;
	}
	
	public List<CommentVO> getLast3CommentsForOccasion(Long occasionId) {

		return connectionProvider.getJdbcTemplate().query(
				GET_CUSTOM_MESSAGES_SQL, new Object[] { occasionId },
				new BeanPropertyRowMapper(CommentVO.class));
	}
	
	public Boolean addContribution(ContributionDO contribution) {

		int rowsAffected = connectionProvider.getJdbcTemplate().update(
				INSERT_CONTRIBUTION_SQL,
				new Object[] { contribution.getOccasionId(),
						contribution.getContactPersonId(),
						contribution.getInvolvedPersonId(),
						contribution.getName(), contribution.getPhoneNumber(),
						contribution.getEmail(), contribution.getAddress(),
						contribution.getCustomMessage(),
						contribution.getPaymentMethod(),
						contribution.getAmountPledged() });
		return rowsAffected == 1;
	}
}
