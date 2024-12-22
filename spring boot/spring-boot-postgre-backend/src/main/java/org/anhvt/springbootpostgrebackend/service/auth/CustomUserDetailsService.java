package org.anhvt.springbootpostgrebackend.service.auth;

import org.anhvt.springbootpostgrebackend.entity.auth.User;
import org.anhvt.springbootpostgrebackend.exception.BusinessException;
import org.anhvt.springbootpostgrebackend.model.CustomUserDetails;
import org.anhvt.springbootpostgrebackend.service.user.UserService;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        LOGGER.info("loadUserByUsername: {}", username);
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new BusinessException(ResponseCode.USER_NOT_FOUND.getCode(),
                        String.format(String.format("Username %s not found", username))));
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return mapUserToCustomUserDetails(user, authorities);
    }

    private CustomUserDetails mapUserToCustomUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getId());
        customUserDetails.setUsername(user.getUsername());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setName(user.getName());
        customUserDetails.setEmail(user.getEmail());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }
}