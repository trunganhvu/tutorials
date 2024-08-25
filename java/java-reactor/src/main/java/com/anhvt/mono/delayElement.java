/**
 * Copyright 2024
 * Name: delayElement
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Create a Mono which delays an onNext signal by a given duration on a default Scheduler and completes.
 * A mono has created > do delay > emit data
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class delayElement {
    public static void main(String[] args) {
        Mono.just("Hello, Reactor!")
                .delayElement(Duration.ofSeconds(2))
                .subscribe(System.out::println);        // Hello, Reactor!

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

