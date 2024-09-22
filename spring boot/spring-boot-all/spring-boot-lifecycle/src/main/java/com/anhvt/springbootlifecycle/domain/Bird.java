/**
 * Copyright 2024
 * Name: Bird
 */
package com.anhvt.springbootlifecycle.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/21/2024
 */
public class Bird implements InitializingBean, DisposableBean {
    private String name;

    public Bird() {
        System.out.println("Bird constructor1");
    }

    public Bird(String name) {
        this.name = name;
        System.out.println("Bird constructor2");
    }

    public String getName() {
        return name;
    }

    @Override
    public void destroy() {
        System.out.println("Bird destroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Bird afterPropertiesSet");
    }
}
