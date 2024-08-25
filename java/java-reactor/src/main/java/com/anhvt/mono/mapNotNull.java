/**
 * Copyright 2024
 * Name: mapNotNull
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Transform the item emitted by this Mono by applying a synchronous function to it,
 * which is allowed to produce a null value.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class mapNotNull {
    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(20);

        // Map every elements with action
        // Enable use null value and not have any exceptions
        Mono<Integer> resultMono = mono.mapNotNull(value -> {
            if (value % 2 == 0) {
                return value * 2;
            } else {
                return null;
            }
        });
        resultMono.subscribe(
                value -> System.out.println("Result: " + value),    // Result: 20 or not print if value is ODD
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")               // Completed
        );
    }
}
