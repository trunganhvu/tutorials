/**
 * Copyright 2024
 * Name: doOnSubscribe
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Add behavior (side-effect) triggered when the Mono is being subscribed,
 * that is to say when a Subscription has been produced by the Publisher and is being passed to the Subscriber.onSubscribe(Subscription).
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnSubscribe {
    public static void main(String[] args) {
        // Create a Mono
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnSubscribe(subscription -> {
                    // Perform additional tasks when the Mono is subscribed
                    System.out.println("Subscribed");
                });

        // Subscribe to the Mono
        mono.subscribe(
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
