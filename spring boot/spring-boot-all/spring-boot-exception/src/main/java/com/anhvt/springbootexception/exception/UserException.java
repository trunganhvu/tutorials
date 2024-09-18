/**
 * Copyright 2024
 * Name: UserException
 */
package com.anhvt.springbootexception.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/18/2024
 */
@Getter
@Setter
public class UserException extends CommonException{
    private final String userId;
    public UserException(String code, String message, HttpStatus status, String userId) {
        super(code, message, status);
        this.userId = userId;
    }
}
