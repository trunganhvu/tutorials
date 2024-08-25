/**
 * Copyright 2024
 * Name: doOnCancel
 */
package com.anhvt.flux;

import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

/**
 * Add behavior (side-effect) triggered when the Flux is cancelled.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnCancel {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C")
                .doOnCancel(() -> System.out.println("Cancelled"));// Define action when cancelled

        flux.subscribe(
                System.out::println,                                // A B C
                error -> System.err.println("Error: " + error),     // not print
                () -> System.out.println("Completed")               // Completed
        );

        // When onCancel a mono do not subscribe
        Disposable disposable = flux.subscribe(
                System.out::println,                                // not print
                error -> System.err.println("Error: " + error),     // not print
                () -> System.out.println("Completed"),              // not print
                Subscription::cancel                                // called doOnCancel - Cancelled
        );

        System.out.println(disposable.isDisposed());    // false
        disposable.dispose();                           // Cancelled
        System.out.println(disposable.isDisposed());    // true
        /**
         * Output:
         * A
         * B
         * C
         * Completed
         * Cancelled
         * false
         * Cancelled
         * true
         */
    }
}
