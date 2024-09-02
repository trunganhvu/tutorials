/**
 * Copyright 2024
 * Name: just
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class just {
    public static void main(String[] args) {
        // Create mono just value
        Flux<String> firstFlux = Flux.just("Hello", "A");

        // Subscribe to mono with onNext, onError, onComplete
        firstFlux.subscribe(
                value -> System.out.println("firstFlux value: " + value), // firstFlux value: Hello     firstFlux value: A
                error -> System.out.println("firstFlux error: " + error), // not print
                () -> System.out.println("firstFlux completed")           // firstFlux completed
        );

        // Flux just null value
        try {
            Flux<String> secondFlux = Flux.just(null);      // throw NullPointerException
        } catch (NullPointerException exception) {
            System.out.println("Just null");                     // Just null
        }
    }
}
