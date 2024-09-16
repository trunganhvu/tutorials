/**
 * Copyright 2024
 * Name: ObjectController
 */
package com.anhvt.springbootswagger.controller;

import com.anhvt.springbootswagger.model.CommonRequest;
import com.anhvt.springbootswagger.model.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/16/2024
 */
@RestController
@RequestMapping("/Object")
public class UserController {
    @Operation(summary = "Get Object by ID", description = "Retrieve a Object by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Object found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "404", description = "Object not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Object>> getObjectById(@PathVariable Long id) {
        // Implementation here
        CommonResponse<Object> response = new CommonResponse<>();
        response.setStatus("success");
        response.setMessage("Object found");
        response.setData(new Object()); // Replace with actual Object
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create a new Object", description = "Create a new Object with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Object created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<CommonResponse<Object>> createObject(
            @RequestBody @Schema(description = "Request body containing Object details", implementation = CommonRequest.class) CommonRequest<Object> request) {
        // Implementation here
        CommonResponse<Object> response = new CommonResponse<>();
        response.setStatus("success");
        response.setMessage("Object created");
        response.setData(request.getData()); // Replace with actual Object
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing Object", description = "Update the Object details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Object updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "404", description = "Object not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<Object>> updateObject(
            @PathVariable Long id,
            @RequestBody @Schema(description = "Request body containing updated Object details", implementation = CommonRequest.class) CommonRequest<Object> request) {
        // Implementation here
        CommonResponse<Object> response = new CommonResponse<>();
        response.setStatus("success");
        response.setMessage("Object updated");
        response.setData(request.getData()); // Replace with actual Object
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a Object", description = "Delete the Object by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Object deleted"),
            @ApiResponse(responseCode = "404", description = "Object not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteObject(@PathVariable Long id) {
        // Implementation here
        CommonResponse<Void> response = new CommonResponse<>();
        response.setStatus("success");
        response.setMessage("Object deleted");
        return ResponseEntity.ok().body(response);
    }
}
