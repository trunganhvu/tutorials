/**
 * Copyright 2024
 * Name: zip
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Concat multi publisher (not depend data type) to one publisher
 *
 * @author trunganhvu
 * @date 8/24/2024
 */
public class zip {
    public static void main(String[] args) {
        Mono<String> mono1 = Mono.just("Hello");
        Mono<String> mono2 = Mono.just("World");
        Mono<Integer> mono3 = Mono.just(2);     // Integer

        Mono.zip(mono1, mono2, (s1, s2) -> s1 + " " + s2)
                .subscribe(result -> System.out.println("Result: " + result));  // Result: Hello World

        Mono.zip(mono1, mono2, mono3)   // max 8 param publisher
                .subscribe(result -> System.out.println("Result: " + result));  // Result: [Hello,World,2]
    }
}
