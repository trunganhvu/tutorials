package IntStream;

import java.util.stream.IntStream;

/**
 * Get count item in stream
 */
public class count {
    public static void main(String[] args) {

        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        long count = intStream.count();
        System.out.println("Count: " + count);  // 10

        // IntStream ís simple traversal
        IntStream.range(1, 10).forEach(System.out::print);  // 123456789


    }
}
