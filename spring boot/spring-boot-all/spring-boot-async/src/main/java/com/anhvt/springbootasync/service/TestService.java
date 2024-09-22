/**
 * Copyright 2024
 * Name: TestService
 */
package com.anhvt.springbootasync.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/21/2024
 */
@Service
public class TestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async("asyncThreadPoolTaskExecutor")
    // @Async
    public Future<String> asyncMethod() {
        sleep();
        logger.info("Service asyncMethod: {}", Thread.currentThread().getName());
        return new AsyncResult<>("hello async");
    }

    public Future<String> syncMethod() {
        sleep();
        logger.info("Service syncMethod: {}", Thread.currentThread().getName());
        return new AsyncResult<>("hello sync");

    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
