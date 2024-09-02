/**
 * Copyright 2024
 * Name: doOnSubscribe
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class doOnSubscribe {
    public static void main(String[] args) {
        // Create a Flux
        Flux<String> flux = Flux.just("Hello, Reactor!", "A")
                .doOnSubscribe(subscription -> {
                    // Perform additional tasks when the Flux is subscribed
                    System.out.println("Subscribed");
                });

        // Subscribe to the Flux
        flux.subscribe(
                value -> System.out.println("Value: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed"),
                subscription -> subscription.request(1)
        );
        /**
         * Output:
         * Subscribed
         * Value: Hello, Reactor!
         * Completed
         */
    }
}
