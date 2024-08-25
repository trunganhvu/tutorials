/**
 * Copyright 2024
 * Name: doOnNext
 */
package com.anhvt.mono;


import reactor.core.publisher.Mono;

/**
 * Add behavior triggered when the Mono emits a data successfully.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnNext {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnNext(doOnNext::updateState);

        mono.subscribe(System.out::println);
        /**
         * Output:
         * State updated with value: Hello, Reactor!
         * Hello, Reactor!
         */
    }

    private static void updateState(String value) {
        System.out.println("State updated with value: " + value);
    }
}
