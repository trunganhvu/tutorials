package org.anhvt.springbootelk;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot application launcher
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootElkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElkApplication.class, args);
    }
    /**
     * A servlet filter that inserts various values retrieved from the incoming http
     * request into the MDC
     *
     * @return {@link FilterRegistrationBean}
     */
    @Bean
    public FilterRegistrationBean userInsertingMdcFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        MDCInsertingServletFilter userFilter = new MDCInsertingServletFilter();
        registrationBean.setFilter(userFilter);
        return registrationBean;
    }
}
