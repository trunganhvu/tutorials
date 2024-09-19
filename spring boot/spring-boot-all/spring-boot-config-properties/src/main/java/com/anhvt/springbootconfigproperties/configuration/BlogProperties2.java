/**
 * Copyright 2024
 * Name: BlogProperties2
 */
package com.anhvt.springbootconfigproperties.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/19/2024
 */
@ConfigurationProperties(prefix="data.blog")
@Setter
@Getter
public class BlogProperties2 {
    private String name;
    private String title;
    private String header;
}
