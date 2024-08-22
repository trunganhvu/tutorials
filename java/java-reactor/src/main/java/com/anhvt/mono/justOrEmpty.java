/**
 * Copyright 2024
 * Name: justOrEmpty
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Create a new Mono that emits the specified item if it is not null
 * otherwise only emits onComplete.
 *
 * @author trunganhvu
 * @date 8/22/2024
 */
public class justOrEmpty {
    public static void main(String[] args) {
        String nullValue = null;

        // if value is not null have result the same with just()
        // otherwise do nothing in onNext, onError and DID onComplete

        // 1: NULL value
        Mono<String> firstMono = Mono.justOrEmpty(nullValue);           // have a warning
        // Subscribe to mono with onNext, onError, onComplete
        firstMono.subscribe(
                value -> System.out.println("firstMono value: " + value), // not print
                error -> System.out.println("firstMono error: " + error), // not print
                () -> System.out.println("firstMono completed")           // firstMono completed
        );

        // 2: Not null value
        Mono<String> secondMono = Mono.justOrEmpty("secondMono");
        // Subscribe to mono with onNext, onError, onComplete
        secondMono.subscribe(
                value -> System.out.println("secondMono value: " + value), // secondMono value: secondMono
                error -> System.out.println("secondMono error: " + error), // not print
                () -> System.out.println("secondMono completed")           // firstMono completed
        );

        // 3: Optional empty value same with NULL value
        Mono<String> threeMono = Mono.justOrEmpty(Optional.empty());
        threeMono.subscribe(
                value -> System.out.println("threeMono value: " + value), // not print
                error -> System.out.println("threeMono error: " + error), // not print
                () -> System.out.println("threeMono completed")           // threeMono completed
        );

        // 4: Optional empty value same with NULL value
        Mono<String> fourMono = Mono.justOrEmpty(Optional.of("fourMono"));
        fourMono.subscribe(
                value -> System.out.println("fourMono value: " + value), // fourMono value: fourMono
                error -> System.out.println("fourMono error: " + error), // not print
                () -> System.out.println("fourMono completed")           // fourMono completed
        );
    }
}
