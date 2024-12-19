package org.anhvt.springbootpostgrebackend.controller.auth;

import jakarta.validation.Valid;
import org.anhvt.springbootpostgrebackend.config.security.SecurityConfig;
import org.anhvt.springbootpostgrebackend.config.security.TokenProvider;
import org.anhvt.springbootpostgrebackend.entity.User;
import org.anhvt.springbootpostgrebackend.payload.request.auth.LoginRequest;
import org.anhvt.springbootpostgrebackend.payload.request.auth.SignUpRequest;
import org.anhvt.springbootpostgrebackend.payload.response.APIResponse;
import org.anhvt.springbootpostgrebackend.payload.response.auth.AuthResponse;
import org.anhvt.springbootpostgrebackend.service.user.UserService;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public APIResponse<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.username(), loginRequest.password());
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response(new AuthResponse(token))
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public APIResponse<Object> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithUsername(signUpRequest.username())) {
            throw new RuntimeException(String.format("Username %s already been used", signUpRequest.username()));
        }
        if (userService.hasUserWithEmail(signUpRequest.email())) {
            throw new RuntimeException(String.format("Email %s already been used", signUpRequest.email()));
        }

        userService.saveUser(mapSignUpRequestToUser(signUpRequest));

        String token = authenticateAndGetToken(signUpRequest.username(), signUpRequest.password());
        return APIResponse.builder()
                .status(ResponseCode.OK.getCode())
                .message(ResponseCode.OK.getMessage())
                .response(new AuthResponse(token))
                .build();
    }

    private String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generate(authentication);
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