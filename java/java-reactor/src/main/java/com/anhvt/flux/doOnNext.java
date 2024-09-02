/**
 * Copyright 2024
 * Name: doOnNext
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Add behavior triggered when the Mono emits a data successfully.
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class doOnNext {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("Hello, Reactor!", "A")
                .doOnNext(doOnNext::updateState);

        flux.subscribe(System.out::println);
        /**
         * Output:
         * State updated with value: Hello, Reactor!
         * Hello, Reactor!
         * State updated with value: A
         * A
         */
    }

    private static void updateState(String value) {
        System.out.println("State updated with value: " + value);
    }
}
