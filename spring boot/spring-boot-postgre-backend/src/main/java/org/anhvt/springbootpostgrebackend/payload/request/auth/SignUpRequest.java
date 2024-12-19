package org.anhvt.springbootpostgrebackend.payload.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @Schema(example = "username") @NotBlank String username,
        @Schema(example = "password") @NotBlank String password,
        @Schema(example = "username") @NotBlank String name,
        @Schema(example = "username@email.com") @Email String email) {
}
