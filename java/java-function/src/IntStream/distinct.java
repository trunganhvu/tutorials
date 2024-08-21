package IntStream;

import java.util.stream.IntStream;

/**
 * Get distinct value in stream
 * Remove duplication value
 */
public class distinct {
    public static void main(String[] args) {

        IntStream intStream = IntStream.of(1, 2, 3, 2, 4, 5, 3, 6, 7, 6, 8, 9);
        IntStream distinctIntStream = intStream.distinct();
        distinctIntStream.forEach(System.out::print);   // 123456789

    }
}
