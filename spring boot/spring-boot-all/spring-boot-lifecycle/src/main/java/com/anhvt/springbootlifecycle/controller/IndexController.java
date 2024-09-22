/**
 * Copyright 2024
 * Name: IndexController
 */
package com.anhvt.springbootlifecycle.controller;

import com.anhvt.springbootlifecycle.domain.Bird;
import com.anhvt.springbootlifecycle.domain.Fish;
import com.anhvt.springbootlifecycle.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/22/2024
 */
@RestController
public class IndexController {
    @Autowired
    private Bird bird;

    @Autowired
    private Fish fish;

    @Autowired
    private User user;

    @GetMapping("/bird")
    public String bird() {
        return bird.getName();
    }

    @GetMapping("/fish")
    public String fish() {
        return fish.getName();
    }

    @GetMapping("/user")
    public String user() {
        return user.getName();
    }
}
