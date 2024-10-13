/**
 * Copyright 2024
 * Name: WebClientConfig
 */
package com.anhvt.springbootcircuitbreaker.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Map;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

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

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
//                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                .build());
//    }
//    @Bean
//    public CircuitBreakerConfigCustomizer externalServiceFooCircuitBreakerConfig() {
//        return CircuitBreakerConfigCustomizer
//                .of("externalService",
//                        builder -> builder.slidingWindowSize(10)
//                                .slidingWindowType(COUNT_BASED)
//                                .waitDurationInOpenState(Duration.ofSeconds(5))
//                                .minimumNumberOfCalls(5)
//                                .failureRateThreshold(50.0f));
//    }
}
