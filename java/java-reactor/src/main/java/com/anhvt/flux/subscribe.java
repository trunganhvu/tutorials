/**
 * Copyright 2024
 * Name: subscribe
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;
import reactor.util.context.Context;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class subscribe {
    public static void main(String[] args) {
        // 1
        Flux<String> flux = Flux.just("Hello, WebFlux!", "A");
        flux.subscribe();                       // not print
        flux.subscribe(value -> System.out.println("Received: " + value));  // Received: Hello, WebFlux!    Received: A
        flux.subscribe(value -> System.out.println("Received: " + value));  // Received: Hello, WebFlux!    Received: A

        // 3
        Flux<String> flux2 = Flux.just("Hello, WebFlux!", "A")
                .map(value -> {
                    if (value.length() > 10) {
                        throw new RuntimeException("Too long!");
                    }
                    return value;
                });
        flux2.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error.getMessage()) // Error: Too long!
        );

        // 4
        Flux<String> flux3 = Flux.just("Hello, WebFlux!", "A");
        flux3.subscribe(
                value -> System.out.println("Received: " + value),          // Received: Hello, WebFlux!
                error -> System.err.println("Error: " + error.getMessage()),// Not print
                () -> System.out.println("Completed"),                      // Completed
                subscription -> subscription.request(1)                   // Request only one value
        );

        // 5: set context in flux
        Flux<String> flux5 = Flux.deferContextual(ctx -> {
            // Lấy userId từ Context
            String userId = ctx.get("userId");
            return Flux.just("Hello, user with ID: " + userId);
        });

        // Write key value into context using contextWrite
        flux5.contextWrite(Context.of("userId", "12345"))
                .subscribe(
                        System.out::println,                                // Hello, user with ID: 12345
                        Throwable::printStackTrace,
                        () -> System.out.println("Processing complete.")    // Processing complete.
                );

        // Write key value into context using subscribe and Context.of
        flux5.subscribe(
                value -> System.out.println("Received: " + value),          // Received: Hello, user with ID: value111
                error -> System.err.println("Error: " + error.getMessage()),// Not print
                () -> System.out.println("Processing complete."),           // Processing complete.
                Context.of("userId", "value111")
        );
    }
}
