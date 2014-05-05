package com.gift.dal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConnectionProvider {

	final private JdbcTemplate template;

	@Autowired
	public ConnectionProvider(DataSource dataSource) throws Exception {

		template = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {

		try {

			return template;
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}
}