package org.anhvt.demoservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Welcome to demo service");
    }
}
