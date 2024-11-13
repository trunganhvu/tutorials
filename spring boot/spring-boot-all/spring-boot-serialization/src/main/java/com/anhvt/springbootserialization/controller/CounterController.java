/**
 * Copyright 2024
 * Name: CounterController
 */
package com.anhvt.springbootserialization.controller;

import com.anhvt.springbootserialization.entity.Counter;
import com.anhvt.springbootserialization.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 11/13/2024
 */
@RestController
public class CounterController {
    @Autowired
    private CounterRepository counterRepository;

    @GetMapping("/counters")
    public DateResponse getCounter() {
        Counter counter = counterRepository.findAll().get(0);
        return new DateResponse(
                counter.getId(),
                counter.getCounter(),
                counter.getStart(),
                counter.getStart(),
                "  Hello World   ");
    }
}
