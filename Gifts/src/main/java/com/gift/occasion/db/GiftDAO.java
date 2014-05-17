package com.gift.occasion.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gift.dal.ConnectionProvider;

@Repository
public class GiftDAO {

	private static final String GIFT_FOR_OCCASION_SQL = "select * from gift where occasion_id = ? order by price desc";
	private static final String MAX_GIFT_PRICE_FOR_OCCASION_SQL = "select max (price) from gift where occasion_id = ?";
	@Autowired
	ConnectionProvider connectionProvider;

	// TODO: Create DAL framework.

	public List<GiftDO> findGiftsForOccasion(Long occasionId) {

		List<GiftDO> gifts = connectionProvider.getJdbcTemplate().query(GIFT_FOR_OCCASION_SQL,
				new Object[] { occasionId }, new BeanPropertyRowMapper(GiftDO.class));

		return gifts;
	}
	
	public Integer findMaxGiftAmountForOccasion(Long occasionId) {
		
		return connectionProvider.getJdbcTemplate().queryForInt(MAX_GIFT_PRICE_FOR_OCCASION_SQL, new Object[] { occasionId });
	}

}
