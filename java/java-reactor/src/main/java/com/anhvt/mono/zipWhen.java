/**
 * Copyright 2024
 * Name: zipWhen
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.function.Function;

/**
 * Wait for the result from this mono,
 * use it to create a second mono via the provided rightGenerator function and combine both results into a Tuple2.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class zipWhen {
    public static void main(String[] args) {
        Mono<Integer> sourceMono = Mono.just(123456789);
        Function<Integer, Mono<? extends String>> rightGenerator =
                intValue -> Mono.just("Value: " + intValue);

        Mono<Tuple2<Integer, String>> zippedMono = sourceMono.zipWhen(rightGenerator);

        zippedMono.subscribe(
                tuple -> System.out.println("Result: " + tuple.getT1() + ", " + tuple.getT2()), // Result: 123456789, Value: 123456789
                System.err::println
        );
    }
}
