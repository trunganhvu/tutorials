/**
 * Copyright 2024
 * Name: UserService
 */
package com.anhvt.springboottest.service;

import com.anhvt.springboottest.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/22/2024
 */
@Service
public class UserService {
    public List<Object> getUsers() {
        List<Object> users = new ArrayList<Object>();
        users.add(new User(1, "Anhvt1"));
        users.add(new User(2, "Anhvt2"));
        users.add(new User(3, "Anhvt3"));
        return users;
    }
}
