/**
 * Copyright 2024
 * Name: log
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.logging.Level;

/**
 * Log values, errors and completion signal.
 *
 * @author trunganhvu
 * @date 8/22/2024
 */
public class log {
    public static void main(String[] args) {
        // 1:
        Mono<String> firstMono = Mono.just("firstMono")
                .log();
        firstMono.subscribe(
                value -> System.out.println("firstMono value: " + value), // firstMono value: Hello
                error -> System.out.println("firstMono error: " + error), // not print
                () -> System.out.println("firstMono completed")           // firstMono completed
        );
        /** OUTPUT
         * [ INFO] (main) | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
         * [ INFO] (main) | request(unbounded)
         * [ INFO] (main) | onNext(firstMono)
         * firstMono value: firstMono
         * firstMono completed
         * [ INFO] (main) | onComplete()
         */


        // 2:
        Mono<String> secondMono = Mono.just("secondMono1")
                .log("LoggerCategory");
        secondMono.subscribe(
                value -> System.out.println("secondMono value: " + value), // secondMono value: Hello
                error -> System.out.println("secondMono error: " + error), // not print
                () -> System.out.println("secondMono completed")           // secondMono completed
        );
        /** OUTPUT
         * [ INFO] (main) | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
         * [ INFO] (main) | request(unbounded)
         * [ INFO] (main) | onNext(secondMono1)
         * secondMono value: secondMono1
         * secondMono completed
         * [ INFO] (main) | onComplete()
         */
    }
}
