package IntStream;

import java.util.stream.IntStream;

/**
 * Convert IntStream -> DoubleStream
 */
public class asDoubleStream {
    public static void main(String[] args) {
        // IntStream ís simple traversal
        IntStream intStream = IntStream.rangeClosed(1, 10);
        intStream.asDoubleStream() // convert IntStream -> DoubleStream
                .forEach(d -> System.out.printf("%.2f ", d)); // 1.00 2.00 3.00 4.00 5.00 6.00 7.00 8.00 9.00 10.00


        System.out.println();

        IntStream.rangeClosed(1, 10).asDoubleStream()
                .map(Math::sqrt)
                .forEach(d -> System.out.printf("%.2f ", d)); // 1.00 1.41 1.73 2.00 2.24 2.45 2.65 2.83 3.00 3.16
    }
}
