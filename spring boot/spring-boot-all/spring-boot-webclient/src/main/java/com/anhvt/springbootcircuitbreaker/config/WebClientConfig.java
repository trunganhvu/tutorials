/**
 * Copyright 2024
 * Name: WebClientConfig
 */
package com.anhvt.springbootcircuitbreaker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/13/2024
 */
@Configuration
public class WebClientConfig {

    @Bean(name = "webClient")
    public WebClient webClientBuilder() {
        String addressBaseUrl = "http://localhost:8080";
        return WebClient.builder().baseUrl(addressBaseUrl).build();
    }
}
