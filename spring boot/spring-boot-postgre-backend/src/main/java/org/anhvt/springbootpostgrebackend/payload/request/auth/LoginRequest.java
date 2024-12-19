package org.anhvt.springbootpostgrebackend.payload.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Schema(example = "user") @NotBlank String username,
        @Schema(example = "pass") @NotBlank String password) {
}

