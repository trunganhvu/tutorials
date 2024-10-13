/**
 * Copyright 2024
 * Name: ServerService
 */
package com.anhvt.springbootcircuitbreaker.service;

import com.anhvt.springbootcircuitbreaker.entity.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.View;
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

    public Mono<ApiResponse> get() {
        return webClient.get()
                .uri("/client")
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), response ->
                        response.bodyToMono(ApiResponse.class)
                                .flatMap(errorBody -> {
                                    return Mono.error(new Exception("Client Error (4xx): " + errorBody));
                                })
                )
                .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), response ->
                        response.bodyToMono(ApiResponse.class)
                                .flatMap(errorBody -> {
                                    return Mono.error(new Exception("Server Error (5xx): " + errorBody));
                                })
                )
                .bodyToMono(ApiResponse.class)
                .retry(9)
                .onErrorResume(e -> {
                    // Xử lý lỗi toàn cục nếu cần thiết
                    return Mono.just(new ApiResponse(500, "Error occurred: " + e.getMessage()));
                });
    }


}
