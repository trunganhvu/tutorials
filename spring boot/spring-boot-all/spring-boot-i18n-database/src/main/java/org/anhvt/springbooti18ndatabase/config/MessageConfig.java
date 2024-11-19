package org.anhvt.springbooti18ndatabase.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource(DatabaseMessageSource databaseMessageSource) {
        return databaseMessageSource;  // Sử dụng MessageSource tùy chỉnh từ Database
    }
}