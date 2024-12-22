package org.anhvt.springbootpostgrebackend.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

public class SecurityUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    }
//                    else if (authentication.getPrincipal() instanceof String) {
//                        return (String) authentication.getPrincipal();
//                    }
                    LOGGER.info("SecurityUtils.getCurrentUserLogin return null");
                    return null;
                });
    }

    public static String encodeToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));

            String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
            return encoded.length() > 19 ? encoded.substring(0, 19) : encoded;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Encode token failed");
            throw new RuntimeException("Error while encoding token", e);
        }
    }
}
