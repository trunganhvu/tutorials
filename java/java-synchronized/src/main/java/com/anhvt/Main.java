package com.anhvt;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/12/2024
 */
public class Main {
    public class Counter {
        long count = 0;
        public synchronized void add(long value) {
            this.count += value;
        }
    }

    public class CounterThread extends Thread {

        protected Counter counter = null;

        private String name;

        public CounterThread(Counter counter, String name) {
            this.counter = counter;
            this.name = name;
        }

        public void run() {
            for(int i=0; i<10; i++){
                System.out.println(name + " " + counter.count);
                counter.add(1);
            }
        }
    }

    void test() {
        Counter counter = new Counter();
        Thread  threadA = new CounterThread(counter, "A");
        Thread  threadB = new CounterThread(counter, "B");

        threadA.start();
        threadB.start();
        /**
         * Output
         * B 0
         * A 0
         * B 1
         * A 2
         * A 4
         * A 5
         * A 6
         * A 7
         * A 8
         * B 3
         * B 10
         * B 11
         * B 12
         * B 13
         * B 14
         * B 15
         * B 16
         * A 9
         * A 18
         * A 19
         */
    }

    public static void main(String[] args) throws InterruptedException {
//        c1c2();
//        c1c3();
//        c1c4();

        Main main = new Main();
        main.test();
    }

    /**
     * Output:
     * Final count for Counter1: 9529
     * Final count for Counter2: 10000
     * @throws InterruptedException
     */
    static void c1c2() throws InterruptedException {
        Counter1 counter1 = new Counter1();
        Counter2 counter2 = new Counter2();

        // Tạo 10 luồng cho Counter1
        Thread[] threads1 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads1[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter1.increment();
                }
            });
            threads1[i].start();
        }

        // Chờ tất cả các luồng hoàn thành
        for (Thread thread : threads1) {
            thread.join();
        }

        // Kết quả cho Counter1
        System.out.println("Final count for Counter1: " + counter1.getCount());

        // Tạo 10 luồng cho Counter2
        Thread[] threads2 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads2[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter2.increment();
                }
            });
            threads2[i].start();
        }

        // Chờ tất cả các luồng hoàn thành
        for (Thread thread : threads2) {
            thread.join();
        }

        // Kết quả cho Counter2
        System.out.println("Final count for Counter2: " + counter2.getCount());
    }

    /**
     * Output:
     * Final count for Counter1: 9789
     * Final count for Counter2: 10000
     * @throws InterruptedException
     */
    static void c1c3() throws InterruptedException {
        Counter1 counter1 = new Counter1();
        Counter3 counter3 = new Counter3();

        // Tạo 10 luồng cho Counter1
        Thread[] threads1 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads1[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter1.increment();
                }
            });
            threads1[i].start();
        }

        // Chờ tất cả các luồng hoàn thành
        for (Thread thread : threads1) {
            thread.join();
        }

        // Kết quả cho Counter1
        System.out.println("Final count for Counter1: " + counter1.getCount());

        // Tạo 10 luồng cho Counter2
        Thread[] threads2 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads2[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter3.increment();
                }
            });
            threads2[i].start();
        }

        // Chờ tất cả các luồng hoàn thành
        for (Thread thread : threads2) {
            thread.join();
        }

        // Kết quả cho Counter2
        System.out.println("Final count for Counter2: " + counter3.getCount());
    }

    /**
     * Output:
     * Final count for Counter1: 9903
     * Final count for Counter2: 10000
     * @throws InterruptedException
     */
    static void c1c4() throws InterruptedException {
        Counter1 counter1 = new Counter1();
        Counter4 counter4 = new Counter4();

        // Tạo 10 luồng cho Counter1
        Thread[] threads1 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads1[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter1.increment();
                }
            });
            threads1[i].start();
        }

        // Chờ tất cả các luồng hoàn thành
        for (Thread thread : threads1) {
            thread.join();
        }

        // Kết quả cho Counter1
        System.out.println("Final count for Counter1: " + counter1.getCount());

        // Tạo 10 luồng cho Counter2
        Thread[] threads2 = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads2[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter4.increment();
                }
            });
            threads2[i].start();
        }

        // Chờ tất cả các luồng hoàn thành
        for (Thread thread : threads2) {
            thread.join();
        }

        // Kết quả cho Counter2
        System.out.println("Final count for Counter2: " + counter4.getCount());
    }
}