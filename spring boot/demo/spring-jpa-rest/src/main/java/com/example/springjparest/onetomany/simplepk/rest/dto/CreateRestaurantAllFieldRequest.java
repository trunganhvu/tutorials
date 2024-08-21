package com.example.springjparest.onetomany.simplepk.rest.dto;

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
public class CreateRestaurantAllFieldRequest {

    @Schema(example = "Restaurant 1")
    @NotBlank
    private String restaurantName;

    @NotBlank
    @ArraySchema(schema = @Schema(description = "List of dish",
            examples = {"Dish 1", "Dish 2"}, example = "Dish 1"))
    private List<String> dishNames;
}
