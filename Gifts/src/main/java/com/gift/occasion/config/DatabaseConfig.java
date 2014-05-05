package com.gift.occasion.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/gifts");
		dataSource.setUsername("postgres");
		dataSource.setPassword("golfbadi");
		dataSource.setMaxActive(10);
		dataSource.setMaxIdle(3);
		dataSource.setMinIdle(1);
		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeout(60);
		dataSource.setLogAbandoned(true);
		dataSource.setMinEvictableIdleTimeMillis(60000);
		dataSource.setTimeBetweenEvictionRunsMillis(10000);
		dataSource.setNumTestsPerEvictionRun(10);
		dataSource.setMaxWait(5000);
		return dataSource;
	}
}
