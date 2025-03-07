package org.anhvt.springbootentitymanager.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Autowired
    private Environment env;

    @Bean
    public JpaTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager);
        return transactionManager;
    }

    /**
     * Creates the entity manager factory bean which is required to access the JPA
     * functionalities provided by the JPA persistence provider, i.e. Hibernate in
     * this case.
     */
    @Bean(name = "entityManagerFactory")
    @ConditionalOnBean(name = "connectionProvider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("connectionProvider") ConnectionProvider connectionProvider) {

        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();

        // All tenant related entities, repositories and service classes must be scanned
        emfBean.setPackagesToScan(
                "org.anhvt.springbootentitymanager.entity",
                "org.anhvt.springbootentitymanager.repository.user");

        emfBean.setJpaVendorAdapter(jpaVendorAdapter());

        emfBean.setPersistenceUnitName("anhvt" + "-" + "pool");
        LOGGER.debug("PersistenceUnitName [{}]", emfBean.getPersistenceUnitName());

        // for multi tenant
        Map<String, Object> properties = new HashMap<>();
        properties.put(org.hibernate.cfg.Environment.CONNECTION_PROVIDER, connectionProvider);

        emfBean.setJpaPropertyMap(properties);

        // Set hibernate properties
        emfBean.setJpaProperties(DataSourceUtil.hibernateProperties(env));

        return emfBean;
    }

    /**
     * Create JpaVendorAdapter
     */
    private JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }
}
