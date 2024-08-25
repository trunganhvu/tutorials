/**
 * Copyright 2024
 * Name: block
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Subscribe to this Flux and block indefinitely until the upstream signals its first value or completes.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class blockFirst {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C");

        String result = flux.blockFirst(); // Blocks and retrieves the first element
        System.out.println("Blocked result1: " + result);       // Blocked result1: A


        Flux<String> flux1 = Flux.just("A", "B", "C");
        System.out.println("Blocked result2: " + flux1.blockFirst(Duration.ofSeconds(3)));  // Blocked result1: A

    }
}
