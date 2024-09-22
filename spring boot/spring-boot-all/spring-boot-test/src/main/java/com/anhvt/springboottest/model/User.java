/**
 * Copyright 2024
 * Name: User
 */
package com.anhvt.springboottest.model;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/22/2024
 */
public class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
