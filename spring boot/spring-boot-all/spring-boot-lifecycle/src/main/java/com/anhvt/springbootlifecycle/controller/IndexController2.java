/**
 * Copyright 2024
 * Name: IndexController2
 */
package com.anhvt.springbootlifecycle.controller;

import com.anhvt.springbootlifecycle.configuration.WebConfig;
import com.anhvt.springbootlifecycle.domain.Bird;
import com.anhvt.springbootlifecycle.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/22/2024
 */
@RestController
public class IndexController2 {
    @GetMapping("/index2")
    public String index2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        System.out.println("AnnotationConfigApplicationContext");
        User user = context.getBean(User.class);
        System.out.println(user.getName());

        Bird bird = context.getBean(Bird.class);
        System.out.println(bird.getName());
        // IOC
        context.close();
        return user.getName();
    }
}
