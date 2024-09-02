/**
 * Copyright 2024
 * Name: doOnError
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

/**
 * Add behavior triggered when the flux completes with an error matching the given exception type.
 * When flux have a error then publisher not throw exception, doOnError handler logic and error
 *
 * @author trunganhvu
 * @date 9/2/2024
 */
public class doOnError {
    public static void main(String[] args) {

        // <E extends Throwable> Flux<T> doOnError(Class<E> exceptionType, Consumer<? super E> onError)
        Flux<Object> flux = Flux.error(new IllegalArgumentException("Invalid argument"))
                .doOnError(IllegalArgumentException.class, e -> System.out.println("Handled IllegalArgumentException: " + e.getMessage()))
                .doOnError(e -> System.out.println("Handled Throwable: " + e.getMessage()));

        flux.subscribe(System.out::println, System.err::println);   // java.lang.IllegalArgumentException: Invalid argument


        // Flux<T> doOnError(Consumer<? super Throwable> onError)
        Flux.error(new RuntimeException("Error!"))
                .doOnError(e -> System.out.println("Handled Throwable: " + e.getMessage()))
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error)  // Error: java.lang.RuntimeException: Error!
                );


        // Flux<T> doOnError(Predicate<? super Throwable> predicate, Consumer<? super Throwable> onError)
        Flux.error(new RuntimeException("Error!"))
                .doOnError(e -> e instanceof RuntimeException, e -> System.out.println("Handled RuntimeException: " + e.getMessage()))
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.err.println("Error: " + error)  // Error: java.lang.RuntimeException: Error!
                );

    }
}
