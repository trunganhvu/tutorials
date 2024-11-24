package org.anhvt.springbootmasterslavepostgre.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.anhvt.springbootmasterslavepostgre.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.LazyInitializationExcludeFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.anhvt.springbootmasterslavepostgre.repository",
        entityManagerFactoryRef = "writeEntityManagerFactory",
        transactionManagerRef = "writeTransactionManager"
)
@EnableTransactionManagement
public class ReadWriteDataSourceConfig {

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        System.out.println("entityManagerFactoryBuilder");
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDataSource() {
        System.out.println("1.writeDataSource");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DataSource readDataSource() {
        System.out.println("1.readDataSource");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier("writeDataSource") DataSource writeDataSource,
                                 @Qualifier("readDataSource") DataSource readDataSource) {
        System.out.println("dataSource");

        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.WRITE, writeDataSource);
        dataSourceMap.put(DataSourceType.READ, readDataSource);

        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        return routingDataSource;
    }

    @Primary
    @Bean(name = "writeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("writeDataSource") DataSource dataSource) {
        System.out.println("2.writeEntityManagerFactory");
        return builder
                .dataSource(dataSource)
                .packages("org.anhvt.springbootmasterslavepostgre.entity")  // Đảm bảo rằng package của các Entity là đúng
                .persistenceUnit(DataSourceType.WRITE.name())
                .properties(Collections.singletonMap("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"))
                .build();
    }

    // EntityManagerFactory cho Read (Slave)
    @Bean(name = "readEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("readDataSource") DataSource dataSource) {
        System.out.println("2.readEntityManagerFactory");
        return builder
                .dataSource(dataSource)
                .packages("org.anhvt.springbootmasterslavepostgre.entity")
                .persistenceUnit(DataSourceType.READ.name())
                .properties(Collections.singletonMap("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"))
                .build();
    }

    @Primary
    @Bean(name = "writeTransactionManager")
    public PlatformTransactionManager writeTransactionManager(
            @Qualifier("writeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        System.out.println("3.writeTransactionManager");
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "readTransactionManager")
    public PlatformTransactionManager readTransactionManager(
            @Qualifier("readEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        System.out.println("3.readTransactionManager");

        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public LazyInitializationExcludeFilter excludeFilter() {
        return LazyInitializationExcludeFilter.forBeanTypes(HikariDataSource.class);
    }
}