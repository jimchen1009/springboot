package com.ximuyi.demo.datasource;

import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

	@Primary
	@Bean(name = "dataSourcePrimary")
	@ConfigurationProperties(prefix="spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "quartzDataSource")
	@QuartzDataSource
	@ConfigurationProperties(prefix="spring.datasource.quartz")
	public DataSource quartzDataSource() {
		return DataSourceBuilder.create().build();
	}
}
