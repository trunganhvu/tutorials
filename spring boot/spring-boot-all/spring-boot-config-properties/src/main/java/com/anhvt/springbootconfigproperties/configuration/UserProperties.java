/**
 * Copyright 2024
 * Name: UserProperties
 */
package com.anhvt.springbootconfigproperties.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/19/2024
 */
@Configuration
@ConfigurationProperties(prefix="user") // other key which not exist then will be null
@PropertySource("classpath:user.properties")
@Component
@Setter
@Getter
public class UserProperties {
    private String name;
    private int age;
    private String fullname;
}
