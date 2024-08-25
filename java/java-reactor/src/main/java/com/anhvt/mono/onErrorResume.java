/**
 * Copyright 2024
 * Name: onErrorResume
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * Subscribe to a fallback publisher when an error matching the given type occurs,
 * using a function to choose the fallback depending on the error.
 * Map exceptions to normal value which emitted
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class onErrorResume {
    public static void main(String[] args) {
        // 1
        Mono<Object> someMono = Mono.error(new IOException("File not found"))
                .onErrorResume(IOException.class, ex -> {           // Exactly exception
                    System.out.println("Handling IOException: " + ex.getMessage()); // Handling IOException: File not found
                    return Mono.just("Recovered from IOException");
                });
        someMono.subscribe(
                value -> System.out.println("Received value: " + value),    // Received value: Recovered from IOException
                error -> System.err.println("Error: " + error.getMessage())
        );

        // 2
        Mono<Object> someMono1 = Mono.error(new RuntimeException("Something went wrong"))
                .onErrorResume(ex -> {
                    System.out.println("Handling Throwable: " + ex.getMessage());   // Handling Throwable: Something went wrong
                    return Mono.just("Recovered from any Throwable");
                });
        someMono1.subscribe(
                value -> System.out.println("Received value: " + value),    // Received value: Recovered from any Throwable
                error -> System.err.println("Error: " + error.getMessage())
        );


        // 3
        Mono<Integer> someMono2 = Mono.just(10)
                .flatMap(num -> Mono.just(num / 0))
                .onErrorResume(ex -> ex instanceof ArithmeticException, ex -> {
                    System.out.println("Handling ArithmeticException: " + ex.getMessage()); // Handling ArithmeticException: / by zero
                    return Mono.just(0); // Recovered value
                });
        someMono2.subscribe(
                value -> System.out.println("Received value: " + value),    // Received value: 0
                error -> System.err.println("Error: " + error.getMessage())
        );
    }
}
