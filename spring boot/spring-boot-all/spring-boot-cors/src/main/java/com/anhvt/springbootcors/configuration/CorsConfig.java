/**
 * Copyright 2024
 * Name: CorsConfig
 */
package com.anhvt.springbootcors.configuration;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/19/2024
 */
@Configuration
@RequiredArgsConstructor
public class CorsConfig {
    @Bean
    public SecurityFilterChain securityFilterChainGlobalAPI(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)      // Enable CORS with default settings
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF (for APIs; ensure to enable CSRF in forms)
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/index1",
                                "/index2"
                        )
                        .permitAll() // Public endpoints
                        .anyRequest().authenticated() // All other endpoints require authentication
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}