/**
 * Copyright 2024
 * Name: zipDelayError
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Combine two sources via a zipper function, delaying errors from any source until both sources have terminated.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class zipDelayError {
    public static void main(String[] args) {
        Mono<String> mono1 = Mono.just("A");
        Mono<String> mono2 = Mono.error(new RuntimeException("Ops!"));
        Mono<Object> mono3 = Mono.error(new RuntimeException("Ops!")).onErrorReturn("B");

        // Function to define action to zip
        Function<Object[], String> combinator = objects -> Arrays.stream(objects)
                .map(Object::toString)
                .collect(Collectors.joining(" "))
                .trim();

        Mono.zipDelayError(mono1, mono3).subscribe(
                result -> System.out.println("Result: " + result),              // Result: [A,B]
                error -> System.err.println("Error1: " + error.getMessage()) 
        );

        // 1
        Mono<String> zippedMono2 = Mono.zipDelayError(combinator, mono1, mono2);
        zippedMono2.subscribe(
                result -> System.out.println("Result: " + result),
                error -> System.err.println("Error: " + error.getMessage()) // Error: Ops!
        );

        // 2
        Iterable<Mono<?>> monos = List.of(mono1, mono2);
        Mono<String> zippedMono3 = Mono.zipDelayError(monos, combinator);
        zippedMono3.subscribe(
                result -> System.out.println("Result: " + result),
                error -> System.err.println("Error: " + error.getMessage()) // Error: Ops!
        );
    }
}
