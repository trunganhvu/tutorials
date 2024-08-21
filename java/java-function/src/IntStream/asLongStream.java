package IntStream;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * convert IntStream -> LongStream
 */
public class asLongStream {
    public static void main(String[] args) {
        // InteStream as number 1 to 10
        IntStream intStream = IntStream.rangeClosed(1, 10);

        // Convert to LongStream
        LongStream longStream = intStream.asLongStream();
        longStream.forEach(d -> System.out.printf("%d ", d));   // 1 2 3 4 5 6 7 8 9 10


        System.out.println();
        long sum = IntStream.rangeClosed(1, 10).asLongStream().sum();
        System.out.println("Tổng của các số từ 1 đến 10: " + sum);  // 55
    }
}
