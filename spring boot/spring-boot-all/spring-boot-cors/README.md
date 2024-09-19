# CORS
> Cross-Origin Resource Sharing (CORS) cho phép quản lý truy cập giữa các domain tới project

# Cầu hình CORS
Class @Configuration CorsConfig
```java
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
```

# Cầu hình security filter
Bean định nghĩa filter http security (cors, csrf, permit url, authen url)
```java
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
```