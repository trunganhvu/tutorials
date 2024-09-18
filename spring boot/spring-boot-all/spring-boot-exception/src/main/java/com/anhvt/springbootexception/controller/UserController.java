/**
 * Copyright 2024
 * Name: UserController
 */
package com.anhvt.springbootexception.controller;

import com.anhvt.springbootexception.exception.CommonException;
import com.anhvt.springbootexception.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/18/2024
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/exception")
    public void getException() throws Exception {
        throw new Exception("My Exception");
        /**
         * Output:
         * {
         *     "code": "9999",
         *     "message": "My Exception",
         *     "status": "INTERNAL_SERVER_ERROR",
         *     "errorData": null
         * }
         */
    }

    @GetMapping("/common")
    public void getCommonException() {
        throw new CommonException("9000", "My Common Exception", HttpStatus.INTERNAL_SERVER_ERROR);
        /**
         * Output:
         * {
         *     "code": "9000",
         *     "message": "My Common Exception",
         *     "status": "INTERNAL_SERVER_ERROR",
         *     "errorData": null
         * }
         */
    }

    @GetMapping("/user")
    public void getUserException() throws Exception {
        throw new UserException("9100",
                "My User Exception",
                HttpStatus.INTERNAL_SERVER_ERROR,
                "1234567890");
        /**
         * Output:
         * {
         *     "code": "9100",
         *     "message": "My User Exception",
         *     "status": "BAD_REQUEST",
         *     "errorData": {
         *         "data": "1234567890"
         *     }
         * }
         */
    }

    @GetMapping("/io")
    public void getIOException() throws Exception {
        throw new IOException("My IOException");
        /**
         * Output:
         * {
         *     "code": "9000",
         *     "message": "My IOException",
         *     "status": "BAD_REQUEST",
         *     "errorData": null
         * }
         */
    }

    @GetMapping("/null")
    public void getNullPointerException(){
        throw new NullPointerException("My null Exception");    // NullPointer not define then using exception object
        /**
         * Output:
         * {
         *     "code": "9999",
         *     "message": "My null Exception",
         *     "status": "INTERNAL_SERVER_ERROR",
         *     "errorData": null
         * }
         */
    }
}
