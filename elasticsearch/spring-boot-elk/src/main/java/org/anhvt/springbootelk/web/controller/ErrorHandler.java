package org.anhvt.springbootelk.web.controller;

import org.anhvt.springbootelk.exception.ResourceNotFoundException;
import org.anhvt.springbootelk.web.response.CustomErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.google.gson.Gson;

/**
 * Generic error handling mechanism.
 *
 * @author trunganhvu
 */
@ControllerAdvice
class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    private enum ERROR_CODE {
        E0001
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public String handleNotFound(ResourceNotFoundException ex) {
        LOGGER.warn("Entity was not found", ex);
        return new Gson().toJson(new CustomErrorResponse(ERROR_CODE.E0001.name(), ex.getMessage()));
    }

}