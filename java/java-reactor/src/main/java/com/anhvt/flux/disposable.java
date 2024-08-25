/**
 * Copyright 2024
 * Name: disposable
 */
package com.anhvt.flux;

import reactor.core.Disposable;
import reactor.core.Disposables;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * Disposable is present for resource or action async to destroy
 *
 * @author trunganhvu
 * @date 8/25/2024
 */
public class disposable {
    public static void main(String[] args) throws InterruptedException {
        Disposable disposable = searchUsers();

        Thread.sleep(1000);

        System.out.println(disposable.isDisposed());

        disposeResource();
        /**
         * Output:
         * doOnSubscribe
         * user: A
         * user: B
         * complete
         * false
         * Subscribed
         * Disposed: false
         * Disposed: true
         */
    }

    static void disposeResource() {
        Disposable disposable = Flux.just("Hello, World!", "A", "B")          // Setup resource have a delay time
                .delayElements(Duration.ofSeconds(5))
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("Completed"))
                .subscribe();

        try {
            System.out.println("Disposed: " + disposable.isDisposed()); // Disposed: false
            Thread.sleep(2000);         // main thread delay 2s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disposable.dispose();   // dispose thread
        System.out.println("Disposed: " + disposable.isDisposed());     // Disposed: true
    }

    public static Disposable searchUsers() {
        Disposable.Swap disposableSwap = Disposables.swap();

        // Perform the new search asynchronously and log the results
        Disposable nextDisposable = Flux.just("A", "B")
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe"))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(
                        user -> System.out.println("user: " + user),
                        error -> System.out.println("error"),
                        () -> System.out.println("complete")
                );

        // Atomically set the next Disposable on this container and dispose the previous one (if any).
        disposableSwap.update(nextDisposable);

        return disposableSwap;
    }
}
