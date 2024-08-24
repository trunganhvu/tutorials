/**
 * Copyright 2024
 * Name: disposable
 */
package com.anhvt.mono;

import reactor.core.Disposable;
import reactor.core.Disposables;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * Disposable is present for resource or action async to destroy
 * More: https://gist.github.com/aoudiamoncef/92846f1a08ece240a387b291bcc15889
 *
 * @author trunganhvu
 * @date 8/24/2024
 */
public class disposable {
    public static void main(String[] args) throws InterruptedException {
        Disposable disposable = searchUsers();

        // Đợi cho đến khi chuỗi xử lý hoàn thành
        Thread.sleep(1000);

        System.out.println(disposable.isDisposed());

        disposeResource();
    }

    static void disposeResource() {
        Disposable disposable = Mono.just("Hello, World!")          // Setup resource have a delay time
                .delayElement(Duration.ofSeconds(5))
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnSuccess(s -> System.out.println("Received: " + s))
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
        Disposable nextDisposable = Mono.just("DisposableData")
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe"))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(
                        user -> System.out.println("user"),
                        error -> System.out.println("error"),
                        () -> System.out.println("complete")
                );

        // Atomically set the next Disposable on this container and dispose the previous one (if any).
        disposableSwap.update(nextDisposable);

        return disposableSwap;
    }
}
