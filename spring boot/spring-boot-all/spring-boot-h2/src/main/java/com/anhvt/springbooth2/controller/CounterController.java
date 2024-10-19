/**
 * Copyright 2024
 * Name: CounterController
 */
package com.anhvt.springbooth2.controller;

import com.anhvt.springbooth2.entity.Counter;
import com.anhvt.springbooth2.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/19/2024
 */
@RestController
public class CounterController {
    @Autowired
    private CounterRepository counterRepository;

    @GetMapping("/counters")
    public Counter getCounter() {
        return counterRepository.findAll().get(0);
    }
}
