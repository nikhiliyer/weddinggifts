package com.gift.occasion.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gift.dal.ConnectionProvider;

@Repository
public class OccasionDAO {

	@Autowired
	ConnectionProvider connectionProvider;

	// TODO: Create DAL framework.

	public OccasionDO findById(Long occasionId) {

		OccasionDO occasion = connectionProvider.getJdbcTemplate().queryForObject("select * from occasion where id = ?",
				new Object[] { occasionId },
				new BeanPropertyRowMapper(OccasionDO.class));
		return occasion;
	}
	
	public String getSecretKeyForOccasion(Long occasionId) {
		
		OccasionDO occasion = this.findById(occasionId);
		return occasion.getSecretKey();
	}

}
