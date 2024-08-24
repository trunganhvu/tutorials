/**
 * Copyright 2024
 * Name: error
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.util.function.Supplier;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/24/2024
 */
public class error {
    public static void main(String[] args) {
        // 1
        Mono.error(new RuntimeException("MyErrorrr!"))
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error)      // Error: java.lang.RuntimeException: MyErrorrr!
                );

        // 2
        // static <T> Mono<T> error(Supplier<? extends Throwable> errorSupplier)
        Supplier<Throwable> errorSupplier = () -> new RuntimeException("Generated error");
        Mono<String> mono = Mono.error(errorSupplier);
        mono.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error.getMessage()),    // Error: Generated error
                () -> System.out.println("Completed")
        );
    }
}
