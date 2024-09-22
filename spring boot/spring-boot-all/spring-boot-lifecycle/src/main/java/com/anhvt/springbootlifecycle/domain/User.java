/**
 * Copyright 2024
 * Name: User
 */
package com.anhvt.springbootlifecycle.domain;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/21/2024
 */
public class User {
    private String name;

    public User() {
        System.out.println("User constructor1");
    }

    public User(String name) {
        System.out.println("User constructor2");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void init() {
        System.out.println("User init");
    }

    public void destroy() {
        System.out.println("User destroy");
    }
}
