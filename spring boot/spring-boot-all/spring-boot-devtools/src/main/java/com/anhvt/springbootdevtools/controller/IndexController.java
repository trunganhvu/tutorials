/**
 * Copyright 2024
 * Name: IndexController
 */
package com.anhvt.springbootdevtools.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/21/2024
 */
@RestController
public class IndexController {
    @RequestMapping("/index1")
    String index() {
        return "index11";
    }
}
