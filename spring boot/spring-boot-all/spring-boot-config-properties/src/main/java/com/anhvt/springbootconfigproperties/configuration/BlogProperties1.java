/**
 * Copyright 2024
 * Name: BlogProperties
 */
package com.anhvt.springbootconfigproperties.configuration;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/19/2024
 */
@Component
@Getter
public class BlogProperties1 {

    @Value("${data.blog.name}")
    private String name;

    @Value("${data.blog.title}")
    private String title;
}
