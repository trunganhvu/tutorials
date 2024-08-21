package IntStream;

import java.util.stream.IntStream;

/**
 * Sort elements in stream asc order
 * @author AnhVT6
 *
 */
public class sorted {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(5, 3, 8, 1, 9);
        intStream.sorted().forEach(System.out::print);	// 13589
    }
}
