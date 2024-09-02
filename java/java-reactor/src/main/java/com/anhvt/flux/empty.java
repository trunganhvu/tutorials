/**
 * Copyright 2024
 * Name: v
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

import java.util.Optional;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class empty {
    public static void main(String[] args) {
        Flux<Optional<String>> flux = Flux.just(Optional.empty(), Optional.empty(), Optional.of("1"));

        flux.subscribe(
                optionalValue -> {                                              // action value
                    optionalValue.ifPresentOrElse(
                            value -> System.out.println("Received: " + value),  // Received: 1
                            () -> System.out.println("No value present")        // No value present
                    );
                },
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")                       // Completed
        );
        /**
         * OUTPUT:
         * No value present
         * No value present
         * Received: 1
         * Completed
         */
    }
}
