package com.gift.occasion.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gift.dal.ConnectionProvider;

@Repository
public class ContactPersonDAO {
	
	@Autowired
	ConnectionProvider connectionProvider;

	// TODO: Create DAL framework.

	public List<ContactPersonDO> findContactsForOccasion(Long occasionId) {

		List<ContactPersonDO> contacts = connectionProvider.getJdbcTemplate().query("select * from contact_person where occasion_id = ?",
				new Object[] { occasionId }, new BeanPropertyRowMapper(ContactPersonDO.class));

		return contacts;
	}
	
	public ContactPersonDO findById(Long id) {

		ContactPersonDO contactPerson = connectionProvider.getJdbcTemplate().queryForObject("select * from contact_person where id = ?",
				new Object[] { id },
				new BeanPropertyRowMapper(ContactPersonDO.class));
		return contactPerson;
	}

}
