/**
 * Copyright 2024
 * Name: CommonResponse
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
@Schema(description = "Common response format for API responses")
@Data
public class CommonResponse<T> {

    @Schema(description = "Status of the response", example = "success")
    private String status;

    @Schema(description = "Message providing additional information", example = "Operation successful")
    private String message;

    @Schema(description = "Data payload", implementation = Object.class)
    private T data;

    // Getters and setters
}