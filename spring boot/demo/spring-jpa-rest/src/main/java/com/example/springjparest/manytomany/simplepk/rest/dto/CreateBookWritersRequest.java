package com.example.springjparest.manytomany.simplepk.rest.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookWritersRequest {

    @Schema(example = "Introduction to Java 8")
    @NotBlank
    private String bookName;

    @ArraySchema(schema = @Schema(description = "List of writer",
            examples = {"writer 1", "writer 2"}, example = "writer 1"))
    @NotBlank
    private List<String> writerNames;
}
