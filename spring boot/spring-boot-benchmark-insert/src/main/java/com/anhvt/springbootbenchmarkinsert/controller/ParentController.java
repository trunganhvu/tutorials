/**
 * Copyright 2024
 * Name: ParentController
 */
package com.anhvt.springbootbenchmarkinsert.controller;

import com.anhvt.springbootbenchmarkinsert.repository.ParentRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/20/2024
 */
@RestController
@RequestMapping("/parent")
public class ParentController {
    @Autowired
    private ParentRelationRepository parentRelationRepository;

    @GetMapping
    public String count() {
        return String.valueOf(parentRelationRepository.count());
    }
}
