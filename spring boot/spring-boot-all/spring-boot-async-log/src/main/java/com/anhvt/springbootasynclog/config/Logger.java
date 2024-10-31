/**
 * Copyright 2024
 * Name: Logger
 */
package com.anhvt.springbootasynclog.config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/31/2024
 */
@Component
public class Logger {

    @Async
    public void logMessage(String message) {
        System.out.println("message: " + message);
    }
}
