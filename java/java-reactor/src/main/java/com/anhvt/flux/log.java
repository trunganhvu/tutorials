/**
 * Copyright 2024
 * Name: justOrEmpty
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;


/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class log {
    public static void main(String[] args) {
        // 1:
        Flux<String> firstFlux = Flux.just("firstFlux", "A")
                .log();
        firstFlux.subscribe(
                value -> System.out.println("firstFlux value: " + value), // firstFlux value: Hello
                error -> System.out.println("firstFlux error: " + error), // not print
                () -> System.out.println("firstFlux completed")           // firstFlux completed
        );
        /** OUTPUT
         * [ INFO] (main) | onSubscribe([Synchronous Fuseable] FluxArray.ArraySubscription)
         * [ INFO] (main) | request(unbounded)
         * [ INFO] (main) | onNext(firstFlux)
         * firstFlux value: firstFlux
         * [ INFO] (main) | onNext(A)
         * firstFlux value: A
         * [ INFO] (main) | onComplete()
         * firstFlux completed
         */


        // 2:
        Flux<String> secondFlux = Flux.just("secondFlux1","A")
                .log("LoggerCategory");
        secondFlux.subscribe(
                value -> System.out.println("secondFlux value: " + value), // secondFlux value: Hello
                error -> System.out.println("secondFlux error: " + error), // not print
                () -> System.out.println("secondFlux completed")           // secondFlux completed
        );
        /** OUTPUT
         * [ INFO] (main) | onSubscribe([Synchronous Fuseable] FluxArray.ArraySubscription)
         * [ INFO] (main) | request(unbounded)
         * [ INFO] (main) | onNext(secondFlux1)
         * secondFlux value: secondFlux1
         * [ INFO] (main) | onNext(A)
         * secondFlux value: A
         * [ INFO] (main) | onComplete()
         * secondFlux completed
         */
    }
}
