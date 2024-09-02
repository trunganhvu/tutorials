/**
 * Copyright 2024
 * Name: fromIterable
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class fromIterable {
    public static void main(String[] args) {
        Flux<String> fluxStream = Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .filter(s -> s.length() > 0);
        fluxStream.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        /**
         * OUTPUT:
         * Received: Mango
         * Received: Orange
         * Received: Banana
         * Completed
         */

        // 2
        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > 0);

        Flux<String> fluxStream1 = Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData)
                .defaultIfEmpty("Default");
        fluxStream1.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        /**
         * OUTPUT:
         * Received: Mango
         * Received: Orange
         * Received: Banana
         * Completed
         */
    }
}
