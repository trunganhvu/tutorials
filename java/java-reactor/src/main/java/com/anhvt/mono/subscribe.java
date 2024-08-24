/**
 * Copyright 2024
 * Name: subscribe
 */
package com.anhvt.mono;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * Start consumer when publisher emit
 * subscribe: subscription, next, error, complete
 *
 * @author trunganhvu
 * @date 8/24/2024
 */
public class subscribe {
    public static void main(String[] args) {
        // 1
        Mono<String> mono = Mono.just("Hello, WebFlux!");
        mono.subscribe();                       // not print
        mono.subscribe(value -> System.out.println("Received: " + value));  // Received: Hello, WebFlux!
        mono.subscribe(value -> System.out.println("Received: " + value));  // Received: Hello, WebFlux!

        // 2
        Mono.fromRunnable(() -> {
            // Some background task
            System.out.println("Task executed1");   // Task executed1

            try {
                Thread.sleep(10000);            // waiting 10s
                System.out.println("Task executed2");   // Task executed2
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).subscribe();


        // 3
        Mono<String> mono2 = Mono.just("Hello, WebFlux!")
                .map(value -> {
                    if (value.length() > 10) {
                        throw new RuntimeException("Too long!");
                    }
                    return value;
                });
        mono2.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error.getMessage()) // Error: Too long!
        );

        // 4
        Mono<String> mono3 = Mono.just("Hello, WebFlux!");
        mono3.subscribe(
                value -> System.out.println("Received: " + value),          // Received: Hello, WebFlux!
                error -> System.err.println("Error: " + error.getMessage()),// Not print
                () -> System.out.println("Completed"),                      // Completed
                subscription -> subscription.request(1)                   // Request only one value
        );

        // 5: set context in mono
        Mono<String> mono5 = Mono.deferContextual(ctx -> {
            // Lấy userId từ Context
            String userId = ctx.get("userId");
            return Mono.just("Hello, user with ID: " + userId);
        });

        // Write key value into context using contextWrite
        mono5.contextWrite(Context.of("userId", "12345"))
                .subscribe(
                        System.out::println,                                // Hello, user with ID: 12345
                        Throwable::printStackTrace,
                        () -> System.out.println("Processing complete.")    // Processing complete.
                );

        // Write key value into context using subscribe and Context.of
        mono5.subscribe(
                value -> System.out.println("Received: " + value),          // Received: Hello, user with ID: value111
                error -> System.err.println("Error: " + error.getMessage()),// Not print
                () -> System.out.println("Processing complete."),           // Processing complete.
                Context.of("userId", "value111")
        );
    }
}
