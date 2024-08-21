package com.example.springjparest.onetomany.compositepk.rest.dto;

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
public class CreatePlayerAllFieldRequest {

    @Schema(example = "Trump")
    @NotBlank
    private String playerName;

    @ArraySchema(schema = @Schema(description = "List of weapon",
            examples = {"gun1", "gun2"}, example = "gun1"))
    @NotBlank
    private List<String> weaponNames;
}
