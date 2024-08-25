/**
 * Copyright 2024
 * Name: doOnDiscard
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Add behavior triggering (side effect) a Consumer when the value is discarded.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnDiscard {
    public static void main(String[] args) {
        // Create a Mono
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnDiscard(String.class, value -> {
                    // Perform additional tasks when the value is discarded
                    System.out.println("Discarded value: " + value);
                });

        // Subscribe to the Mono
        mono.subscribe(
                System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed"),
                subscription -> subscription.cancel()
        );
    }
}
