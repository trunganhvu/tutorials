package IntStream;

import java.util.stream.IntStream;

/**
 * Limited value to using in stream
 * @author AnhVT6
 *
 */
public class limit {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 100)
                .limit(10);		// Limit using 10 elements

        intStream.forEach(System.out::print);		// 12345678910
    }
}
