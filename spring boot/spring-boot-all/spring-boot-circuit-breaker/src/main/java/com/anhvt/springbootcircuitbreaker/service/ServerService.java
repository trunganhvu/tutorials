/**
 * Copyright 2024
 * Name: ServerService
 */
package com.anhvt.springbootcircuitbreaker.service;

import com.anhvt.springbootcircuitbreaker.entity.ApiResponse;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/13/2024
 */
@Service
public class ServerService {
    @Autowired
    @Qualifier("webClient")
    private WebClient webClient;


    @CircuitBreaker(name = "externalService", fallbackMethod = "fallbackResponse")
    @Retry(name = "clientApiRetry")
    public Mono<ApiResponse> get() {
        return webClient.get()
                .uri("/client")
                .retrieve()
                .onStatus(status -> status.is5xxServerError(), clientResponse ->{
                    return clientResponse.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new RuntimeException("Server error: " + body)));
                })
                .bodyToMono(ApiResponse.class)
                .onErrorResume(e -> {
                    return Mono.just(new ApiResponse(500, e.getMessage()));
                });
    }

    public Mono<ApiResponse> fallbackResponse(Throwable throwable) {
        return Mono.just(new ApiResponse(400,"Fallback response: Service unavailable"));
    }
}
