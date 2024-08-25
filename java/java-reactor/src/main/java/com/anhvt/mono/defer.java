/**
 * Copyright 2024
 * Name: defer
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create a Mono provider for target Mono publisher which handler login before return final Mono
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class defer {
    public static void main(String[] args) {

        // 1:
        Mono<Integer> mono = Mono.defer(() -> {
            System.out.print("Creating Mono");
            return Mono.just(1);
        });
        mono.subscribe(System.out::println); // Creating Mono1


        // 2:
        // Sử dụng Supplier phức tạp hơn
        AtomicInteger counter = new AtomicInteger();
        Mono<Integer> deferredMono = Mono.defer(() -> {
            int value = counter.getAndIncrement();
            return Mono.just(value);
        });

        deferredMono.subscribe(System.out::println); // 0
        deferredMono.subscribe(System.out::println); // 1


        //2:
        Mono<String> result = Mono.defer(() -> {
                    if (Math.random() > 0.5) {
                        return Mono.just("Condition met");
                    } else {
                        return Mono.error(new RuntimeException("Condition not met"));
                    }
                })
                .onErrorResume(throwable -> Mono.just("Condition not met - Processing alternative"))
                .flatMap(message -> Mono.just("Processed: " + message));
        result.subscribe(System.out::println, System.err::println);
    }
}
