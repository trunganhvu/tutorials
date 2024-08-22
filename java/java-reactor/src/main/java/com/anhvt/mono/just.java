/**
 * Copyright 2024
 * Name: just
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Mono.just() to initial value for Mono
 *
 * @author trunganhvu
 * @date 8/22/2024
 */
public class just {
    public static void main(String[] args) {
        // Create mono just value
        Mono<String> firstMono = Mono.just("Hello");

        // Subscribe to mono with onNext, onError, onComplete
        firstMono.subscribe(
                value -> System.out.println("firstMono value: " + value), // firstMono value: Hello
                error -> System.out.println("firstMono error: " + error), // not print
                () -> System.out.println("firstMono completed")           // firstMono completed
        );

        // Mono just null value
        try {
            Mono<String> secondMono = Mono.just(null);      // throw NullPointerException
        } catch (NullPointerException exception) {
            System.out.println("Just null");                     // Just null
        }

        Mono<String> threeMono = Mono.just("Hello")
                .then(Mono.error(new RuntimeException("Error Occurred while publishing data")));

        // Subscribe to mono with onNext, onError, onComplete
        threeMono.subscribe(
                value -> System.out.println("threeMono value: " + value), // not print
                error -> System.out.println("threeMono error: " + error), // threeMono error: java.lang.RuntimeException: Error Occurred while publishing data
                () -> System.out.println("threeMono completed")           // not print
        );
    }
}
