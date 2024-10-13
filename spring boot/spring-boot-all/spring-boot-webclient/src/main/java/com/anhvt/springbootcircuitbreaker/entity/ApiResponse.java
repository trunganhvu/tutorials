/**
 * Copyright 2024
 * Name: ApiResponse
 */
package com.anhvt.springbootcircuitbreaker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/13/2024
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse {
    private int status;
    private String message;
}
