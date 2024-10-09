/**
 * Copyright 2024
 * Name: MyFileNotFoundException
 */
package com.anhvt.springbootuploaddownload.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/9/2024
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
