# Redis với docker
Pull và run image redis
```shell
docker run -d --name my-redis -p 6379:6379  -v /redis/redis.conf:/redis.conf redis redis-server /redis.conf --appendonly yes --requirepass "redis"  --requirepass "password"
```

# Config
Dependency data redis pom.xml
```shell
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

Bổ sung annotation @EnableCaching vào class @SpringBootApplication

## Cấu hình bean factory cho redis
Class RedisConfig
```java
@Bean
public LettuceConnectionFactory redisConnectionFactory() {
    // Host, port, password
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);
    configuration.setPassword(RedisPassword.of(redisPassword));

    return new LettuceConnectionFactory(configuration);
}

@Bean
public RedisCacheManager cacheManager() {
    RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10))
            .disableCachingNullValues();

    return RedisCacheManager.builder(redisConnectionFactory())
            .cacheDefaults(cacheConfig)
            // Key cache with timeout
            .withCacheConfiguration("tutorials", myDefaultCacheConfig(Duration.ofMinutes(5)))
            .withCacheConfiguration("tutorial", myDefaultCacheConfig(Duration.ofMinutes(1)))
            .build();
}
```

## Sử dụng cache cho data trong services
Cache values không parameter
```java
@Cacheable("tutorials")
public List<Tutorial> findAll() {
    doLongRunningTask();
    return tutorialRepository.findAll();
}
```

Cache values có parameter
```java
@Cacheable( value = "tutorial", key = "#id")
public Optional<Tutorial> findById(long id) {
    doLongRunningTask();
    return tutorialRepository.findById(id);
}
```

Xoá cache
```java
@CacheEvict(value = "tutorial", key = "#tutorial.id")
public Tutorial update(Tutorial tutorial) {
    return tutorialRepository.save(tutorial);
}

@CacheEvict(value = { "tutorial", "tutorials" }, allEntries = true)
public void deleteAll() {
    tutorialRepository.deleteAll();
}
```

