/**
 * Copyright 2024
 * Name: TestController
 */
package com.anhvt.springbootasync.controller;

import com.anhvt.springbootasync.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/21/2024
 */
@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestService testService;

    @GetMapping("async")
    public String testAsync() throws Exception {
        long start = System.currentTimeMillis();
        logger.info("testAsync: start");

        Future<String> stringFuture = testService.asyncMethod();
        String result = stringFuture.get(60, TimeUnit.SECONDS);
        logger.info("testAsync: {}", result);

        long end = System.currentTimeMillis();
        logger.info("testAsync: end - start = {} ms", end - start);
        return stringFuture.get();
    }

    @GetMapping("sync")
    public String testSync() throws ExecutionException, InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        logger.info("testSync: start");

        Future<String> stringFuture = testService.syncMethod();
        String result = stringFuture.get(60, TimeUnit.SECONDS);
        logger.info("testSync: {}", result);

        long end = System.currentTimeMillis();
        logger.info("testSync: end - start = {} ms", end - start);

        return stringFuture.get();
    }

}