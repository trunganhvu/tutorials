/**
 * Copyright 2024
 * Name: MultiDataSourceConfig
 */
package com.anhvt.springbootaoplog.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
//@Configuration
//@EnableTransactionManagement
public class MultiDataSourceConfig {

//    @Primary
//    @Bean(name = "mysqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public DataSource mysqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "mysqlEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("mysqlDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.anhvt.springall7.SpringBootAOPLog.domain.mysql")
//                .persistenceUnit("mysql")
//                .build();
//    }
//
//    @Bean(name = "mysqlTransactionManager")
//    public PlatformTransactionManager mysqlTransactionManager(
//            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//    @Primary
//    @Bean(name = "postgresqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
//    public DataSource postgresqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "postgresqlEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("postgresqlDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("your.package.to.postgresql.entities")
//                .persistenceUnit("postgresql")
//                .build();
//    }
//
//    @Bean(name = "postgresqlTransactionManager")
//    public PlatformTransactionManager postgresqlTransactionManager(
//            @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}