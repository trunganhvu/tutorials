/**
 * Copyright 2024
 * Name: delay
 */
package com.anhvt.mono;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * Create a Mono which delays an onNext signal by a given duration on a default Scheduler and completes
 * After delay a mono has created and emit
 * Do delay > A mono has created > emit data
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class delay {
    public static void main(String[] args) {
        // 1
        // Delay 2s to set string to Mono
        Mono.delay(Duration.ofSeconds(2))
                .justOrEmpty("Hello")
                .subscribe(System.out::println);    // Hello

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // 2
        Scheduler scheduler = Schedulers.boundedElastic();

        Mono<String> result = Mono.delay(Duration.ofSeconds(2), scheduler)
                .justOrEmpty("Hello1")
                .subscribeOn(scheduler);
        result.subscribe(System.out::println);  // Hello1

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
