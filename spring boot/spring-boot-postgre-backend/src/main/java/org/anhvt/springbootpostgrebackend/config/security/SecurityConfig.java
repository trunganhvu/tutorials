package org.anhvt.springbootpostgrebackend.config.security;

import org.anhvt.springbootpostgrebackend.config.filter.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static final String[] whiteList = {"/api/articles/**", "/auth/**",
            "/", "/error", "/csrf", "/swagger-ui.html", "/swagger-ui/**",
            "/v3/api-docs", "/v3/api-docs/**"};

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST, "/api/orders").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers(HttpMethod.GET, "/api/users/me").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers("/api/users", "/api/users/**").hasAuthority(ADMIN)
                        .requestMatchers(whiteList).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionHandlingConfigurer ->
                        exceptionHandlingConfigurer
                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                )
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
