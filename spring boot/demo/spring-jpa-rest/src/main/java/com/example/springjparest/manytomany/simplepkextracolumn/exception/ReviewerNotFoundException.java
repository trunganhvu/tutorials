package com.example.springjparest.manytomany.simplepkextracolumn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewerNotFoundException extends RuntimeException {

    public ReviewerNotFoundException(String id) {
        super(String.format("Reviewer with id '%s' not found", id));
    }
}
