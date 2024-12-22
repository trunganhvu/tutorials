package org.anhvt.springbootpostgrebackend.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.anhvt.springbootpostgrebackend.config.security.SecurityConfig;
import org.anhvt.springbootpostgrebackend.entity.auth.User;
import org.anhvt.springbootpostgrebackend.payload.request.auth.LoginRequest;
import org.anhvt.springbootpostgrebackend.payload.request.auth.LogoutRequest;
import org.anhvt.springbootpostgrebackend.payload.request.auth.RefreshTokenRequest;
import org.anhvt.springbootpostgrebackend.payload.request.auth.SignUpRequest;
import org.anhvt.springbootpostgrebackend.payload.response.APIResponse;
import org.anhvt.springbootpostgrebackend.payload.response.auth.AuthResponse;
import org.anhvt.springbootpostgrebackend.service.auth.AuthenticationService;
import org.anhvt.springbootpostgrebackend.service.user.UserService;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationService authenticateService;

    @PostMapping("/login")
    public APIResponse<Object> login(@Valid @RequestBody LoginRequest loginRequest,
                                     HttpServletRequest request) {
        LOGGER.info("Login Controller");
        AuthResponse response = authenticateService.authenticate(loginRequest.username(),
                loginRequest.password(), request);
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response(response)
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public APIResponse<Object> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        LOGGER.info("Signup Controller");
        if (userService.hasUserWithUsername(signUpRequest.username())) {
            LOGGER.info("Username already been used");
            throw new RuntimeException(String.format("Username %s already been used", signUpRequest.username()));
        }
        if (userService.hasUserWithEmail(signUpRequest.email())) {
            LOGGER.info("Email already been used");
            throw new RuntimeException(String.format("Email %s already been used", signUpRequest.email()));
        }

        userService.saveUser(mapSignUpRequestToUser(signUpRequest));
        LOGGER.info("Signup success");
//        AuthResponse response = authenticateService.authenticate(signUpRequest.username(),
//                signUpRequest.password());
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response("Signup success")
                .build();
    }

    @PostMapping("/refresh")
    public APIResponse<Object> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        LOGGER.info("RefreshToken Controller");
        AuthResponse response = authenticateService.refreshToken(refreshTokenRequest);
        LOGGER.info("RefreshToken success");
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response(response)
                .build();
    }

    @PostMapping("/logout")
    public APIResponse<Object> logout(HttpServletRequest request,
                                      @Valid @RequestBody LogoutRequest logoutRequest) {
        LOGGER.info("Logout Controller");
        authenticateService.logout(request, logoutRequest);
        LOGGER.info("Logout success");
        return APIResponse.builder()
                .status(ResponseCode.LOGOUT_SUCCESS.getCode())
                .message(ResponseCode.LOGOUT_SUCCESS.getMessage())
                .build();
    }

    private User mapSignUpRequestToUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.username());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        user.setName(signUpRequest.name());
        user.setEmail(signUpRequest.email());
        user.setRole(SecurityConfig.USER);
        return user;
    }
}