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
public class CreateWriterBooksRequest {

    @Schema(example = "Writer 1")
    @NotBlank
    private String writerName;

    @ArraySchema(schema = @Schema(description = "List of book",
            examples = {"book 1", "book 2"}, example = "book 1"))
    @NotBlank
    private List<String> bookNames;
}
