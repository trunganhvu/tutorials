/**
 * Copyright 2024
 * Name: CommonRequest
 */
package com.anhvt.springbootswagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/16/2024
 */
@Data
@Schema(description = "Common request format for API requests")
public class CommonRequest<T> {

    @Schema(description = "Request payload", implementation = Object.class)
    private T data;

    // Getters and setters
}

