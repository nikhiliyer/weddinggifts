package com.gift.occasion.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gift.dal.ConnectionProvider;

@Repository
public class EventDAO {

	@Autowired
	ConnectionProvider connectionProvider;

	// TODO: Create DAL framework.

	public List<EventDO> findEventsForOccasion(Long occasionId) {

		List<EventDO> events = connectionProvider.getJdbcTemplate().query("select * from event where occasion_id = ?",
				new Object[] { occasionId }, new BeanPropertyRowMapper(EventDO.class));

		return events;
	}
}
