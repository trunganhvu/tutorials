/**
 * Copyright 2024
 * Name: fromCallable
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

/**
 * Create a Mono producing its value using the provided Callable.
 * Create a Mono from Callable
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class fromCallable {
    public static void main(String[] args) {
        // 1
        Mono<String> mono = Mono.fromCallable(() -> {
            return "Hello, Reactor!";
        }).onErrorResume(e -> {
            return Mono.just("Error occurred!");
        });

        mono.subscribe(
                value -> System.out.println("Received: " + value),  // Received: Hello, Reactor!
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")               // Completed
        );


        // 2
        Callable<Integer> callable = () -> {
            Thread.sleep(1000);
            return 123;
        };
        Mono<Integer> mono2 = Mono.fromCallable(callable);
        mono2.subscribe(
                value -> System.out.println("Received: " + value),  // Received: 123
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")               // Completed
        );
    }
}
