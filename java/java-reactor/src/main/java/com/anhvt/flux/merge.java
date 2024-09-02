/**
 * Copyright 2024
 * Name: merge
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class merge {
    public static void main(String[] args) {
        var fruits = Flux.just("Mango","Orange");
        var veggies = Flux.just("Tomato","Lemon", "0000");
        Flux.merge(fruits,veggies).subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        /**
         * OUTPUT:
         * Received: Mango
         * Received: Orange
         * Received: Tomato
         * Received: Lemon
         * Received: 0000
         * Completed
         */
    }
}
