package org.anhvt.springbootpostgrebackend.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.anhvt.springbootpostgrebackend.entity.auth.User;
import org.anhvt.springbootpostgrebackend.model.CustomUserDetails;
import org.anhvt.springbootpostgrebackend.payload.dto.user.UserDto;
import org.anhvt.springbootpostgrebackend.payload.response.APIResponse;
import org.anhvt.springbootpostgrebackend.service.user.UserService;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.anhvt.springbootpostgrebackend.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("/me")
    public APIResponse<Object> getCurrentUser(@AuthenticationPrincipal CustomUserDetails currentUser) {
        LOGGER.info("Current user controller");
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response(toUserDto(userService.validateAndGetUserByUsername(currentUser.getUsername())))
                .build();
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping
    public APIResponse<Object> getUsers() {
        LOGGER.info("Get users controller");
        List<UserDto> result = userService.getUsers().stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());

        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response(result)
                .build();
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("/{username}")
    public APIResponse<Object> getUser(@PathVariable String username) {
        LOGGER.info("Get user controller");
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response(toUserDto(userService.validateAndGetUserByUsername(username)))
                .build();
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping("/{username}")
    public APIResponse<Object> deleteUser(@PathVariable String username) {
        LOGGER.info("Delete user controller");
        User user = userService.validateAndGetUserByUsername(username);
        userService.deleteUser(user);
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response(toUserDto(user))
                .build();
    }

    private UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getRole());
    }
}