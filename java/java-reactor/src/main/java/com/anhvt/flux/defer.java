/**
 * Copyright 2024
 * Name: defer
 */
package com.anhvt.flux;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lazily supply a Publisher every time a Subscription is made on the resulting Flux,
 * so the actual source instantiation is deferred until each subscribe and
 * the Supplier can create a subscriber-specific instance.
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class defer {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        Flux<Integer> deferredFlux = Flux.defer(() -> {
            int value = counter.getAndIncrement();
            return Flux.just(value);
        });

        deferredFlux.subscribe(System.out::println); // 0
        deferredFlux.subscribe(System.out::println); // 1
        deferredFlux.subscribe(System.out::println); // 2
        deferredFlux.subscribe(System.out::println); // 3
    }
}
