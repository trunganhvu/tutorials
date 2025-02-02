package org.anhvt.springbootentitymanager.config;

import io.micrometer.common.util.StringUtils;
import lombok.experimental.UtilityClass;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import java.util.Properties;
import java.util.Map;

@UtilityClass
public class DataSourceUtil {

    private static final Map<String, String> REDISSON_PROPERTIES_MAP = Map.of(
            "HIBERNATE_CACHE_REDISSON_ENTITY_EVICTION_MAX_ENTRIES",
            "hibernate.cache.redisson.entity.eviction.max_entries",
            "HIBERNATE_CACHE_REDISSON_ENTITY_EXPIRATION_TIME_TO_LIVE",
            "hibernate.cache.redisson.entity.expiration.time_to_live",
            "HIBERNATE_CACHE_REDISSON_ENTITY_EXPIRATION_MAX_IDLE_TIME",
            "hibernate.cache.redisson.entity.expiration.max_idle_time",
            "HIBERNATE_CACHE_REDISSON_QUERY_EVICTION_MAX_ENTRIES",
            "hibernate.cache.redisson.query.eviction.max_entries",
            "HIBERNATE_CACHE_REDISSON_QUERY_EXPIRATION_TIME_TO_LIVE",
            "hibernate.cache.redisson.query.expiration.time_to_live",
            "HIBERNATE_CACHE_REDISSON_QUERY_EXPIRATION_MAX_IDLE_TIME",
            "hibernate.cache.redisson.query.expiration.max_idle_time",
            "HIBERNATE_SHOW_SQL",
            "hibernate.show_sql",
            "HIBERNATE_FORMAT_SQL",
            "hibernate.format_sql");

    /**
     * The properties for configuring the JPA provider Hibernate.
     */
    public static Properties hibernateProperties(Environment env) {
        Properties properties = new Properties();
        properties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.MySQLInnoDBDialect");
//        boolean isShowSql = env.acceptsProfiles(Profiles.of(ApplicationConstants.LOCAL_PROFILE));
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
        properties.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "none");

        properties.put(org.hibernate.cfg.Environment.CONNECTION_PROVIDER_DISABLES_AUTOCOMMIT, true);
        properties.put(org.hibernate.cfg.Environment.USE_SECOND_LEVEL_CACHE, true);
        properties.put(org.hibernate.cfg.Environment.USE_QUERY_CACHE, true);
        properties.put(org.hibernate.cfg.Environment.GENERATE_STATISTICS, false);

//        REDISSON_PROPERTIES_MAP.forEach((key, value) -> setRedissonProperty(env, properties, key));

        return properties;
    }

//    @SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
//    private static void setRedissonProperty(Environment env, Properties properties, String property) {
//        if (REDISSON_PROPERTIES_MAP.containsKey(property) && StringUtils.isNotEmpty(env.getProperty(property))) {
//            properties.put(REDISSON_PROPERTIES_MAP.get(property), env.getProperty(property));
//        }
//    }
}
