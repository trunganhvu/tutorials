/**
 * Copyright 2024
 * Name: doOnDiscard
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Potentially modify the behavior of the whole chain of operators upstream of this one
 * to conditionally clean up elements that get discarded by these operators.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnDiscard {
    public static void main(String[] args) {
        // Create a Flux
        Flux<String> flux = Flux.just("Hello, Reactor!", "A", "B")
                .doOnDiscard(String.class, value -> {
                    System.out.println("Discarded value: " + value);
                });

        // Subscribe to the Flux
        flux.take(2).subscribe(
                System.out::println,                // Hello, Reactor! A
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        /**
         * Output:
         * Hello, Reactor!
         * A
         * Completed
         */
    }
}
