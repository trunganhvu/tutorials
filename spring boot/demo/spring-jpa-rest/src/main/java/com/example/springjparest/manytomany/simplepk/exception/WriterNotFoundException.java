package com.example.springjparest.manytomany.simplepk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WriterNotFoundException extends RuntimeException {

    public WriterNotFoundException(String id) {
        super(String.format("Writer with id '%s' not found", id));
    }
}
