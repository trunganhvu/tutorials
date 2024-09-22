/**
 * Copyright 2024
 * Name: Fish
 */
package com.anhvt.springbootlifecycle.domain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/21/2024
 */
public class Fish {

    private String name;

    public Fish() {
        System.out.println("Fish constructor1");
    }

    public Fish(String name) {
        System.out.println("Fish constructor2");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fish PostConstruct");
    }

    @PreDestroy
    public void destory() {
        System.out.println("Fist PreDestroy");
    }
}
