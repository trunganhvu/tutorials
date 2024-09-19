/**
 * Copyright 2024
 * Name: IndexController
 */
package com.anhvt.springbootactuator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/19/2024
 */
@RestController
public class IndexController {
    @RequestMapping("/my-index")
    String index() {
        return "hello world";
    }
}
