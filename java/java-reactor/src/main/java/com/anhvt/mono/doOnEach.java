/**
 * Copyright 2024
 * Name: doOnEach
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

/**
 * Add behavior (side-effect) triggered when the Mono emits an item, fails with an error, or completes successfully.
 * Do somethings to every elements
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnEach {
    public static void main(String[] args) {
        // Create a Mono
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnEach(signal -> eachFunction(signal));
        mono.subscribe();
        /**
         * Output:
         * Emitted value: Hello, Reactor!
         * Completed
         */

        Mono<String> mono1 = Mono.just("Hello, Reactor1!")
                .doOnEach(signal -> eachFunction(signal));
        mono1.subscribe();
        /**
         * Output:
         * Emitted value: Hello, Reactor1!
         * Completed
         */
    }

    static void eachFunction(Signal signal) {
        // Perform additional tasks when the Mono emits an item, fails with an error, or completes successfully
        if (signal.isOnNext()) {
            System.out.println("Emitted value: " + signal.get());
        } else if (signal.isOnError()) {
            System.err.println("Error: " + signal.getThrowable());
        } else {
            System.out.println("Completed");
        }
    }
}
