/**
 * Copyright 2024
 * Name: as
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Convert publisher to other publisher which different type
 * Ex: publisher string -> publisher int
 *
 * @author trunganhvu
 * @date 8/24/2024
 */
public class as {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, World!");

        // Convert Mono<String> to Mono<Integer> using length of string
        Function<Mono<String>, Mono<Integer>> stringLengthTransformer =
                stringMono -> stringMono.map(String::length);

        // Create new mono by function and base mono
        Mono<Integer> lengthMono = mono.as(stringLengthTransformer);

        lengthMono.subscribe(
                length -> System.out.println("Length of the string: " + length));  // Length of the string: 13

    }
}
