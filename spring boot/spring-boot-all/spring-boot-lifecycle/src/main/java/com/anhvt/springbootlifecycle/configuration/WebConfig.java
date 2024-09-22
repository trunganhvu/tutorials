/**
 * Copyright 2024
 * Name: WebConfig
 */
package com.anhvt.springbootlifecycle.configuration;

import com.anhvt.springbootlifecycle.domain.Bird;
import com.anhvt.springbootlifecycle.domain.Fish;
import com.anhvt.springbootlifecycle.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/21/2024
 */
@Configuration
public class WebConfig {

     @Scope("prototype")
     @Bean(initMethod = "init", destroyMethod = "destroy")
     public User user() {
         System.out.println("WebConfig - User");
         return new User("C");
     }

     @Bean
     public Bird bird() {
         System.out.println("WebConfig - Bird");
         return new Bird("A");
     }

    @Bean
    public Fish fish(){
        System.out.println("WebConfig - Fish");
        return new Fish("B");
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor () {
        System.out.println("WebConfig - MyBeanPostProcessor");
        return new MyBeanPostProcessor();
    }
}
