/**
 * Copyright 2024
 * Name: CommonException
 */
package com.anhvt.springbootexception.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Common exception class
 *
 * @author trunganhvu
 * @date 9/18/2024
 */
@Getter
@Setter
public class CommonException extends RuntimeException{
    private final String code;
    private final String message;
    private final HttpStatus status;

    public CommonException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
