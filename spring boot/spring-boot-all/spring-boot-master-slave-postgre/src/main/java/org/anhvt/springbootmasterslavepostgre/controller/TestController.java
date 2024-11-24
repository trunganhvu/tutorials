package org.anhvt.springbootmasterslavepostgre.controller;

import org.anhvt.springbootmasterslavepostgre.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/{id}")
    public Object test(@PathVariable Long id) {
        return testService.findById(id);
    }

    @GetMapping("/save/{id}/{name}")
    public Object save(@PathVariable Long id, @PathVariable String name) {
        testService.save(id, name);
        return "success";
    }
}
