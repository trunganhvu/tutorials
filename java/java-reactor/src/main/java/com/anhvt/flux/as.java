/**
 * Copyright 2024
 * Name: and
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Transform this Flux into a target type.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class as {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C");

        flux.as(f -> f.map(String::toLowerCase))
                .subscribe(System.out::println);    // a b c
    }
}
