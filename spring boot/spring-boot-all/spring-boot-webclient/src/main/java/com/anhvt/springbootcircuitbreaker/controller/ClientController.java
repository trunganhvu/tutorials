/**
 * Copyright 2024
 * Name: ClientController
 */
package com.anhvt.springbootcircuitbreaker.controller;

import com.anhvt.springbootcircuitbreaker.entity.ApiResponse;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/client")
public class ClientController {
    private int index = 0;

    /**
     * Controller return http status of sequence
     * @return
     * @throws Exception
     */
    @GetMapping
    public ResponseEntity<ApiResponse> hello() throws Exception {
        index++;
        if (index % 5 != 1) {
            return ResponseEntity.ok(new ApiResponse(200, "Hello World"));
        }
        return new ResponseEntity<>(new ApiResponse(500, "500"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
