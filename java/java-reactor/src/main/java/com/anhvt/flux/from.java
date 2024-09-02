/**
 * Copyright 2024
 * Name: from
 */
package com.anhvt.flux;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/2/2024
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
                        s.onNext("Hello from custom Publisher1!");
                        s.onNext("Hello from custom Publisher2!");
                        s.onComplete();
                    }
                    @Override
                    public void cancel() {
                        // Handle cancel
                    }
                });
            }
        };

        // Create a Flux from Publisher and set more actions
        Flux<String> fluxStream = Flux.from(customPublisher)
                .doOnRequest(r -> System.out.println("new request"))
                .doOnSubscribe(r -> System.out.println("new subscribe"));

        fluxStream.subscribe(
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
         * Received: Hello from custom Publisher1!
         * Received: Hello from custom Publisher2!
         * Completed
         */

        // Create a Flux from Publisher and not set more actions
        Flux<String> fluxStream1 = Flux.from(customPublisher);
        fluxStream1.subscribe(
                value -> System.out.println("Received: " + value),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        /**
         * Output:
         * In request-Publisher
         * Received: Hello from custom Publisher!
         * Received: Hello from custom Publisher1!
         * Received: Hello from custom Publisher2!
         * Completed
         */
    }
}
