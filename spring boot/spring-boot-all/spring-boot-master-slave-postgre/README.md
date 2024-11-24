# Setup master-slave database
Tham khảo hướng dẫn trong folder replication

# Dependencies, properties, and configuration
```properties
spring.datasource.write.jdbc-url=jdbc:postgresql://192.168.1.9:5000/postgresdb
spring.datasource.write.username=postgresadmin
spring.datasource.write.password=admin123
spring.datasource.write.driver-class-name=org.postgresql.Driver
spring.datasource.write.jpa.hibernate.ddl-auto=update
spring.datasource.write.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Read DataSource Configuration (Slave)
spring.datasource.read.jdbc-url=jdbc:postgresql://192.168.1.9:5001/postgresdb
spring.datasource.read.username=postgresadmin
spring.datasource.read.password=admin123
spring.datasource.read.driver-class-name=org.postgresql.Driver
spring.datasource.read.jpa.hibernate.ddl-auto=update
spring.datasource.read.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Dependencies: postgresql, jpa

## Configuration
> ReadWriteDataSourceConfig > ReplicationRoutingDataSource > DataSourceContextHolder > DataSourceAspect

### Read/Write DataSource Configuration
```java
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
```

### Write/Read EntityManagerFactory
```java
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
```

### Write/Read TransactionManager
```java
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
```

### Class ReplicaConfig commented out, but can be used in other way

## Transaction
### Access to master database are save, update, delete repositories
### Access to slave database are find, get repositories using @Transactional(readOnly = true)
