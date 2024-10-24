/**
 * Copyright 2024
 * Name: DataSourceConfig
 */
package com.anhvt.springbootjdbctemplatemultidatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/24/2024
 */
@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "mysqldatasource")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "h2datasource")
    @ConfigurationProperties("spring.datasource.druid.h2")
    public DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("mysqldatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "h2JdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("h2datasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
