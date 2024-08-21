package IntStream;

import java.util.stream.IntStream;

/**
 * Defind stream with value in range [start, end]
 * @author AnhVT6
 *
 */
public class rangeClosed {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 5);
        intStream.forEach(System.out::print);	// 12345
    }
}
