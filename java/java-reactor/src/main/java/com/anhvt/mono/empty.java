/**
 * Copyright 2024
 * Name: empty
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Using Optional.empty as null value
 *
 * @author trunganhvu
 * @date 8/24/2024
 */
public class empty {
    public static void main(String[] args) {
        Mono<Optional<String>> mono = Mono.just(Optional.empty());

        mono.subscribe(
                optionalValue -> {                                              // action value
                    optionalValue.ifPresentOrElse(
                            value -> System.out.println("Received: " + value),  // not print
                            () -> System.out.println("No value present")        // No value present
                    );
                },
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")                       // Completed
        );
    }
}
