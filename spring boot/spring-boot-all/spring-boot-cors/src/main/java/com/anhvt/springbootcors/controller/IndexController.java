/**
 * Copyright 2024
 * Name: IndexController
 */
package com.anhvt.springbootcors.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/19/2024
 */
@RestController
public class IndexController {

    @GetMapping("/index1")
    public String index1() {
        return "index1";
    }

    @GetMapping("/index2")
    public String index2() {
        return "index2";
    }

    @GetMapping("/index3")
    public String index3() {
        return "index3";
    }
}
