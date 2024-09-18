/**
 * Copyright 2024
 * Name: UserController
 */
package com.anhvt.springbootfilterinterceptor.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/18/2024
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/{id}")
    public void get(@PathVariable String id, HttpServletRequest request) {
        System.out.println("UserController :" + id);
        System.out.println("UserController :" + request.getAttributeNames());

        // throw new RuntimeException("user not exist");
    }
}
