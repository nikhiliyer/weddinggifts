package com.gift.occasion.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gift.dal.ConnectionProvider;

@Repository
public class InvolvedPersonDAO {

	@Autowired
	ConnectionProvider connectionProvider;

	public List<InvolvedPersonDO> findPeopleInvolvedInOccasion(Long occasionId) {

		List<InvolvedPersonDO> involvedPeople = connectionProvider
				.getJdbcTemplate().query(
						"select * from involved_person where occasion_id = ?",
						new Object[] { occasionId },
						new BeanPropertyRowMapper(InvolvedPersonDO.class));

		return involvedPeople;
	}

}
