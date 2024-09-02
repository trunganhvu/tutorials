/**
 * Copyright 2024
 * Name: map
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class map {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(20, 21, 22);

        // Map every elements with action
        // Can not using null value -> throw NullPointerException
        Flux<Integer> resultFlux = flux.map(value -> {
            if (value % 2 == 0) {
                return value * 2;
            } else {
                return null;    // throw NullPointerException
            }
        });

        resultFlux.subscribe(
                value -> System.out.println("Result: " + value),    // Result: 40
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")               // Completed
        );

        /**
         * OUTPUT:
         * Result: 40
         * Error: java.lang.NullPointerException: The mapper [com.anhvt.flux.map$$Lambda$22/0x000001376102cf68] returned a null value.
         */


        // 2
        Flux<Integer> resultFlux1 = flux.map(value -> {
            if (value % 2 == 0) {
                return value * 2;
            } else {
                return value;
            }
        });

        resultFlux1.subscribe(
                value -> System.out.println("Result: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        /**
         * OUTPUT:
         * Result: 40
         * Result: 21
         * Result: 44
         * Completed
         */
    }
}
