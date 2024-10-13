/**
 * Copyright 2024
 * Name: ServerController
 */
package com.anhvt.springbootcircuitbreaker.controller;

import com.anhvt.springbootcircuitbreaker.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/13/2024
 */
@RestController
@RequestMapping("/server")
public class ServerController {
    @Autowired
    private ServerService serverService;

    @GetMapping
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok(serverService.get().block());
    }
}
