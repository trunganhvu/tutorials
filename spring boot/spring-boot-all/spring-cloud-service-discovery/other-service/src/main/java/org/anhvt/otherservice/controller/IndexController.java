package org.anhvt.otherservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public String hello() {
        return "hello in other service";
    }

    @GetMapping("/hi")
    public String hi() {
        return "hi in other service";
    }
}
