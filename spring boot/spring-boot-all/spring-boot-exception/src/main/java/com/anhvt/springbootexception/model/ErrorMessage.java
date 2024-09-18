package com.anhvt.springbootexception.model;

import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/18/2024
 */
public record ErrorMessage(String code, String message, HttpStatus status, Optional<ErrorData> errorData) {
    public record ErrorData(Object data) {}
}
