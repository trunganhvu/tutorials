/**
 * Copyright 2024
 * Name: IndexController
 */
package com.anhvt.springbootaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/16/2024
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    /**
     * Log Output:
     * Before execution - Roles: []
     *
     * @return
     */
    @GetMapping
    public String index() {
        return "index";
    }
}
