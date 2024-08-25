/**
 * Copyright 2024
 * Name: block
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Subscribe to this Mono and block indefinitely until a next signal is received.
 *
 * @author trunganhvu
 * @date 8/24/2024
 */
public class block {
    public static void main(String[] args) {
        // 1
        Mono<Integer> mono = Mono.just(10);

        Integer result = mono.block();

        System.out.println(result); // 10

        // 2
        Mono<String> delayedMono = Mono.just("Delayed Hello")
                .delayElement(Duration.ofSeconds(2));

        System.out.println("Waiting for the result...");
        String result1 = delayedMono.block();

        System.out.println("Result: " + result1);   // Result: Delayed Hello
    }
}
