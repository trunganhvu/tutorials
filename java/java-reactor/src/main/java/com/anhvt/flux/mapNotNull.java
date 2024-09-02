/**
 * Copyright 2024
 * Name: mapNotNull
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class mapNotNull {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(20, 10, 40);

        // Map every elements with action
        // Enable use null value and not have any exceptions
        Flux<Integer> resultFlux = flux.mapNotNull(value -> {
            if (value % 2 == 0) {
                return value * 2;
            } else {
                return null;
            }
        });
        resultFlux.subscribe(
                value -> System.out.println("Result: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")               // Completed
        );
        /**
         * OUTPUT:
         * Result: 40
         * Result: 20
         * Result: 80
         * Completed
         */
    }
}
