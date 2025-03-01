package org.anhvt.spring.controller;

import org.anhvt.spring.entity.Greeting;
import org.anhvt.spring.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private GreetingRepository repository;

    @GetMapping
    public List<Greeting> showHome() {
        return repository.findAll();
    }
}
