/**
 * Copyright 2024
 * Name: CounterController
 */
package com.anhvt.springboottransaction.controller;

import com.anhvt.springboottransaction.entity.Counter;
import com.anhvt.springboottransaction.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
@RestController
public class CounterController {

    @Autowired
    private CounterService counterService;

    @GetMapping("/counters")
    public Counter getCounter() {
        return counterService.getCounter();
    }

    @PostMapping("/counters")
    public Counter createCounter() throws InterruptedException {
        return counterService.incrementCount();
    }
}
