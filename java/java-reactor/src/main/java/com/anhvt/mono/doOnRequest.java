/**
 * Copyright 2024
 * Name: doOnRequest
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Add behavior triggering a LongConsumer when the Mono receives any request.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnRequest {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnRequest(request -> {
                    if (request <= 0) {
                        throw new IllegalArgumentException("Request amount must be positive");
                    }
                    System.out.println("Valid request amount: " + request);
                });

        mono.subscribe(
                value -> System.out.println("Value: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed"),
                subscription -> subscription.request(1)
        );
        /**
         * Output:
         * Valid request amount: 1
         * Value: Hello, Reactor!
         * Completed
         */
    }
}
