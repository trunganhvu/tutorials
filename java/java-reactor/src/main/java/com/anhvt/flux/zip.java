/**
 * Copyright 2024
 * Name: zip
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class zip {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("Hello", "A");
        Flux<String> flux2 = Flux.just("World", "B", "C");
        Flux<Integer> flux3 = Flux.just(2, 0);     // Integer

        Flux.zip(flux1, flux2, (s1, s2) -> s1 + " " + s2)
                .subscribe(result -> System.out.println("Result: " + result));  // Result: Hello World      Result: A B

        Flux.zip(flux1, flux2, flux3)   // max 8 param publisher
                .subscribe(result -> System.out.println("Result: " + result));  // Result: [Hello,World,2]      Result: [A,B,0]
    }
}
