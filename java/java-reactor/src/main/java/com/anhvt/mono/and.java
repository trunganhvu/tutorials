/**
 * Copyright 2024
 * Name: and
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

/**
 * method and() is Mono1 & Mono2, not care of value in them
 * And() focus to publisher have been finished
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class and {
    public static void main(String[] args) {
        // 1
        Mono<Integer> mono1 = Mono.just(10);
        Mono<Integer> mono2 = Mono.just(20);

        // and method not emit value and only callback completed method
        mono1.and(mono2)
                .subscribe(
                value -> System.out.println("value: " + value),                 // not print
                error -> System.err.println("error: " + error.getMessage()),    // not print
                () -> System.out.println("completed")                           // completed
        );

        // 2
        Mono<Void> mono3 = Mono.fromRunnable(() -> {
            System.out.println("Task 1 is complete");
        });

        Mono<Void> mono4 = Mono.fromRunnable(() -> {
            System.out.println("Task 2 is complete");
        });

        mono3.and(mono4)
                .doFirst(
                        () -> System.out.println("Do first")
                )
                .doOnSubscribe(subscription ->
                        System.out.println("Do subscription: " + subscription)
                )
                .doOnTerminate(() -> {
                    System.out.println("Do terminate");
                })
                .doOnSuccess(value ->
                        System.out.println("Do success: " + value)
                )
                .doOnCancel(
                        () -> System.out.println("Do cancel")
                )
                .subscribe();
        /**
         * Output:
         * Do first
         * Do subscription: reactor.core.publisher.MonoWhen$WhenCoordinator@e6ea0c6
         * Task 1 is complete
         * Task 2 is complete
         * Do terminate
         * Do success: null
         */
    }
}
