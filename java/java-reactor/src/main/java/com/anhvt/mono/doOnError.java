/**
 * Copyright 2024
 * Name: doOnError
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * Add behavior triggered when the Mono completes with an error matching the given exception type.
 * When mono have a error then publisher not throw exception, doOnError handler logic and error
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnError {
    public static void main(String[] args) {

        // <E extends Throwable> Mono<T> doOnError(Class<E> exceptionType, Consumer<? super E> onError)
        Mono<Object> mono = Mono.error(new IllegalArgumentException("Invalid argument"))
                .doOnError(IllegalArgumentException.class, e -> System.out.println("Handled IllegalArgumentException: " + e.getMessage()))
                .doOnError(e -> System.out.println("Handled Throwable: " + e.getMessage()));

        mono.subscribe(System.out::println, System.err::println);


        // Mono<T> doOnError(Consumer<? super Throwable> onError)
        Mono.error(new RuntimeException("Error!"))
                .doOnError(e -> System.out.println("Handled Throwable: " + e.getMessage()))
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error)
                );


        // Mono<T> doOnError(Predicate<? super Throwable> predicate, Consumer<? super Throwable> onError)
        Mono.error(new RuntimeException("Error!"))
                .doOnError(e -> e instanceof RuntimeException, e -> System.out.println("Handled RuntimeException: " + e.getMessage()))
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error)
                );

    }
}
