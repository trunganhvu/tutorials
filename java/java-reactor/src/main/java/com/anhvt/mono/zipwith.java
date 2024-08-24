/**
 * Copyright 2024
 * Name: zipwith
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * zipWith from a publisher to add other publisher to this
 * zip: Mono.zip
 * zipWith: variable.zipWith
 *
 * @author trunganhvu
 * @date 8/24/2024
 */
public class zipwith {
    public static void main(String[] args) {
        Mono<String> mono1 = Mono.just("Hello");
        Mono<Integer> mono2 = Mono.just(5);

        mono1.zipWith(mono2, (s, i) -> s + " World, Number: " + i)
                .subscribe(result -> System.out.println("Result: " + result));      // Result: Hello World, Number: 5
    }
}
