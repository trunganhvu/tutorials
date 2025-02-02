package org.anhvt.springbootentitymanager.controller;

import org.anhvt.springbootentitymanager.repository.user.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    @GetMapping
    public int totalSearch(String keyword) {
        return userRepositoryCustom.totalSearch(keyword);
    }
}
