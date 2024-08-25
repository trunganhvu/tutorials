/**
 * Copyright 2024
 * Name: map
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Transform the item emitted by this Mono by applying a synchronous function to it.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class map {
    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(20);

        // Map every elements with action
        // Can not using null value -> throw NullPointerException
        Mono<Integer> resultMono = mono.map(value -> {
            if (value % 2 == 0) {
                return value * 2;
            } else {
                return null;    // throw NullPointerException
            }
        });

        resultMono.subscribe(
                value -> System.out.println("Result: " + value),    // Result: 20
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")               // Completed
        );
    }
}
