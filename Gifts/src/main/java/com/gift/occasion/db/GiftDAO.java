package com.gift.occasion.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gift.dal.ConnectionProvider;

@Repository
public class GiftDAO {

	@Autowired
	ConnectionProvider connectionProvider;

	// TODO: Create DAL framework.

	public List<GiftDO> findGiftsForOccasion(Long occasionId) {

		List<GiftDO> gifts = connectionProvider.getJdbcTemplate().query("select * from gift where occasion_id = ? order by price desc",
				new Object[] { occasionId }, new BeanPropertyRowMapper(GiftDO.class));

		return gifts;
	}

}
