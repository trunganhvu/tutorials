package org.anhvt.springbootpostgrebackend.payload.response.auth;

public record AuthResponse(String accessToken, String refreshToken) {
}
