package com.example.springjparest.onetoone.sharedpk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String id) {
        super(String.format("Person with id '%s' not found", id));
    }
}