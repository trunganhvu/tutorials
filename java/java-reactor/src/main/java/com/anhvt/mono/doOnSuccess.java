/**
 * Copyright 2024
 * Name: doOnSuccess
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Add behavior triggered as soon as the Mono can be considered to have completed successfully.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnSuccess {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnSuccess(value -> {
                    System.out.println("Success with value: " + value);
                    sendNotification(value);
                });

        mono.subscribe(
                value -> System.out.println("Value: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed"),
                subscription -> subscription.request(1)
        );
        /**
         * Outout:
         * Success with value: Hello, Reactor!
         * Notification sent for value: Hello, Reactor!
         * Value: Hello, Reactor!
         * Completed
         */
    }

    private static void sendNotification(String value) {
        System.out.println("Notification sent for value: " + value);
    }
}
