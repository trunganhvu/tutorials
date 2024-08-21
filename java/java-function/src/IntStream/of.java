package IntStream;

import java.util.stream.IntStream;

/**
 * Defind a Stream with specific values
 * @author AnhVT6
 *
 */
public class of {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        intStream.forEach(System.out::println);
    }
}
