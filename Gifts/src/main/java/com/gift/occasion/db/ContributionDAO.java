package com.gift.occasion.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gift.dal.ConnectionProvider;

@Repository
public class ContributionDAO {

	@Autowired
	ConnectionProvider connectionProvider;

	@Autowired
	ContactPersonDAO contactPersonDAO;

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
				.getJdbcTemplate()
				.query("select name, total_amount_pledged, total_amount_collected from (select sum(amount_pledged) as total_amount_pledged, sum(amount_collected) as total_amount_collected, involved_person_id from contribution where occasion_id = ? group by involved_person_id) a, involved_person p where a.involved_person_id = p.id",
						new Object[] { occasionId },
						new BeanPropertyRowMapper(
								ContributionsByInvolvedPersonVO.class));
		TotalContributionsVO totalContributions = new TotalContributionsVO(contributionsByInvolvedPerson);
		return totalContributions;
	}
	
	public List<String> getLast3CommentsForOccasion(Long occasionId) {

		return connectionProvider
				.getJdbcTemplate()
				.queryForList(
						"select custom_message from contribution where occasion_id = ? order by created_on desc",
						new Object[] { occasionId }, String.class);
	}
}
