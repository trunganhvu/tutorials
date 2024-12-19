package org.anhvt.springbootpostgrebackend.payload.dto.user;

import java.time.Instant;
import java.util.List;

public record UserDto(Long id, String username, String name, String email, String role) {

}
