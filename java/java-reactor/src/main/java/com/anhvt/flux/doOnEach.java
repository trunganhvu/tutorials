/**
 * Copyright 2024
 * Name: doOnEach
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnEach {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C")
                .doOnEach(signal -> System.out.println("Signal: " + signal));
        flux.subscribe(System.out::println);
        /**
         * Output:
         * Signal: doOnEach_onNext(A)
         * A
         * Signal: doOnEach_onNext(B)
         * B
         * Signal: doOnEach_onNext(C)
         * C
         * Signal: onComplete()
         */

        Flux<String> flux1 = Flux.just("A1", "B1", "C1")
                .doOnEach(stringSignal -> eachFunction(stringSignal));
        flux1.subscribe(System.out::println);
        /**
         * Output:
         * Emitted value: A1
         * A1
         * Emitted value: B1
         * B1
         * Emitted value: C1
         * C1
         * Completed
         */

    }

    static void eachFunction(Signal signal) {
        if (signal.isOnNext()) {
            System.out.println("Emitted value: " + signal.get());
        } else if (signal.isOnError()) {
            System.err.println("Error: " + signal.getThrowable());
        } else {
            System.out.println("Completed");
        }
    }
}
