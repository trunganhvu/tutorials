package com.anhvt.springbootconfigproperties;

import com.anhvt.springbootconfigproperties.configuration.BlogProperties2;
import com.anhvt.springbootconfigproperties.configuration.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({BlogProperties2.class, UserProperties.class})
public class SpringBootConfigPropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigPropertiesApplication.class, args);
    }

}
