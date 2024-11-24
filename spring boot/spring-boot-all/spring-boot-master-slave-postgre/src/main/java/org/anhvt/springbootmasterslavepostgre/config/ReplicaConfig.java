//package org.anhvt.springbootmasterslavepostgre.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.anhvt.springbootmasterslavepostgre.enums.DataSourceType;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ReplicaConfig {
//
//    @Value("${spring.datasource.write.url}")
//    private String writeUrl;
//
//    @Value("${spring.datasource.write.username}")
//    private String usernameWrite;
//
//    @Value("${spring.datasource.write.password}")
//    private String passwordWrite;
//
//    @Value("${spring.datasource.write.driver-class-name}")
//    private String driverNameWrite;
//
//    @Value("${spring.datasource.read.url}")
//    private String readUrl;
//
//    @Value("${spring.datasource.read.username}")
//    private String usernameRead;
//
//    @Value("${spring.datasource.read.password}")
//    private String passwordRead;
//
//    @Value("${spring.datasource.read.driver-class-name}")
//    private String driverNameRead;
//
//    @Bean
//    public DataSource writeDataSource() {
//        return DataSourceBuilder
//                .create()
//                .type(HikariDataSource.class)
//                .url(writeUrl)
//                .driverClassName(driverNameWrite)
//                .password(passwordWrite)
//                .username(usernameWrite)
//                .build();
//    }
//
//    @Bean
//    public DataSource readDataSource() {
//        return DataSourceBuilder
//                .create()
//                .type(HikariDataSource.class)
//                .url(readUrl)
//                .driverClassName(driverNameRead)
//                .password(passwordRead)
//                .username(usernameRead)
//                .build();
//    }
//
//    @Bean
//    public DataSource routingDataSource() {
//        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
//
//        Map<Object, Object> dataSourceMap = new HashMap<>();
//        dataSourceMap.put(DataSourceType.WRITE, writeDataSource());
//        dataSourceMap.put(DataSourceType.READ, readDataSource());
//        routingDataSource.setTargetDataSources(dataSourceMap);
//        routingDataSource.setDefaultTargetDataSource(writeDataSource());
//
//        return routingDataSource;
//
//    }
//
//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        return new LazyConnectionDataSourceProxy(routingDataSource());
//    }
//}