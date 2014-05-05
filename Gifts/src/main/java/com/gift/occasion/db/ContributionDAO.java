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
						"select * from contribution where occasion_id = ?",
						new Object[] { occasionId },
						new BeanPropertyRowMapper(ContributionDO.class));

		for (ContributionDO contributionDO : contributions) {

			contributionDO.setContactPerson(contactPersonDAO.findById(contributionDO.getContactPersonId()));
		}
		return contributions;
	}
}
