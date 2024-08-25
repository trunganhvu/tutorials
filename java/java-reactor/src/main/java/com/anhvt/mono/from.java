/**
 * Copyright 2024
 * Name: from
 */
package com.anhvt.mono;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class from {
    public static void main(String[] args) {
        Publisher<String> customPublisher = new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {
                        System.out.println("In request-Publisher");
                        s.onNext("Hello from custom Publisher!");
                        s.onComplete();
                    }
                    @Override
                    public void cancel() {
                        // Handle cancel
                    }
                });
            }
        };

        // Create a Mono from Publisher and set more actions
        Mono<String> monoStream = Mono.from(customPublisher)
                .doOnRequest(r -> System.out.println("new request"))
                .doOnSubscribe(r -> System.out.println("new subscribe"));

        monoStream.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        /**
         * Output:
         * new subscribe
         * new request
         * In request-Publisher
         * Received: Hello from custom Publisher!
         * Completed
         */

        // Create a Mono from Publisher and not set more actions
        Mono<String> monoStream1 = Mono.from(customPublisher);
        monoStream1.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        /**
         * Output:
         * In request-Publisher
         * Received: Hello from custom Publisher!
         * Completed
         */
    }
}
