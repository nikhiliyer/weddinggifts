package com.gift.occasion.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gift.dal.ConnectionProvider;

@Repository
public class ContactPersonDAO {
	
	private static final String PRIMARY_CONTACT_FOR_OCCASION_SQL = "select * from contact_person where occasion_id = ? and primary_contact = true";
	private static final String CONTACT_PERSON_BY_ID_SQL = "select * from contact_person where id = ?";
	private static final String CONTACTS_FOR_OCCASION_SQL = "select * from contact_person where occasion_id = ?";
	@Autowired
	ConnectionProvider connectionProvider;

	// TODO: Create DAL framework.

	public List<ContactPersonDO> findContactsForOccasion(Long occasionId) {

		List<ContactPersonDO> contacts = connectionProvider.getJdbcTemplate().query(CONTACTS_FOR_OCCASION_SQL,
				new Object[] { occasionId }, new BeanPropertyRowMapper(ContactPersonDO.class));

		return contacts;
	}
	
	public ContactPersonDO findById(Long id) {

		ContactPersonDO contactPerson = connectionProvider.getJdbcTemplate().queryForObject(CONTACT_PERSON_BY_ID_SQL,
				new Object[] { id },
				new BeanPropertyRowMapper(ContactPersonDO.class));
		return contactPerson;
	}
	
	public ContactPersonDO findPrimaryContactForOccasion(Long occasionId) {
		
		ContactPersonDO primaryContact = connectionProvider.getJdbcTemplate().queryForObject(PRIMARY_CONTACT_FOR_OCCASION_SQL,
				new Object[] { occasionId }, new BeanPropertyRowMapper(ContactPersonDO.class));

		return primaryContact;
	}

}
