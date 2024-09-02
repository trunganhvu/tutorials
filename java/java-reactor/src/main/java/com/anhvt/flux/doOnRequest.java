/**
 * Copyright 2024
 * Name: doOnRequest
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Add behavior triggering a LongConsumer when the Flux receives any request.
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class doOnRequest {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("Hello, Reactor!", "A")
                .doOnRequest(request -> {
                    if (request <= 0) {
                        throw new IllegalArgumentException("Request amount must be positive");
                    }
                    System.out.println("Valid request amount: " + request);
                });

        flux.subscribe(
                value -> System.out.println("Value: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed"),
                subscription -> subscription.request(1)
        );
        /**
         * Output:
         * Valid request amount: 1
         * Value: Hello, Reactor!
         */
    }
}
