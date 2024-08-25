/**
 * Copyright 2024
 * Name: doOnCancel
 */
package com.anhvt.mono;

import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

/**
 * Add behavior triggered side effects (free stack, disconnection, notification
 * when the Mono is cancelled.
 *
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class doOnCancel {
    public static void main(String[] args) throws InterruptedException {
        // 1
        Mono<String> mono = Mono.just("Hello, Reactor!")
                .doOnCancel(() -> System.out.println("Cancelled")); // Define action when cancelled

        mono.subscribe(
                System.out::println,                                // Hello, Reactor!
                error -> System.err.println("Error: " + error),     // not print
                () -> System.out.println("Completed")               // Completed
        );

        // When onCancel a mono do not subscribe
        mono.subscribe(
                System.out::println,                                // not print
                error -> System.err.println("Error: " + error),     // not print
                () -> System.out.println("Completed"),              // not print
                Subscription::cancel                                // called doOnCancel - Cancelled
        );
        /**
         * Output:
         * Hello, Reactor!
         * Completed
         * Cancelled
         */


        // 2
        Mono<String> monoDemo = Mono.just("Hello")
                .doOnCancel(() -> System.out.println("Cancellation detected!"));

        Disposable disposable = monoDemo.subscribe(
                value -> System.out.println("Value1: " + value),    // Value1: Hello
                error -> System.err.println("Error1: " + error),
                () -> System.out.println("Completed1")              // Completed1
        );

        Thread.sleep(100);
        disposable.dispose();

        /**
         * Output:
         * Value1: Hello
         * Completed1
         */
    }
}
