/**
 * Copyright 2024
 * Name: UserController
 */
package com.anhvt.springboottest.controller;

import com.anhvt.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/22/2024
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
