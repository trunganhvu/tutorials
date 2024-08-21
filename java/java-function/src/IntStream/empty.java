package IntStream;

import java.util.stream.IntStream;

/**
 * Making empty IntStream
 */
public class empty {
    public static void main(String[] args) {
        // Defind IntStream don't have any elements
        IntStream emptyIntStream = IntStream.empty();

        long count = emptyIntStream.count();

        System.out.println("Count: " + count);      // 0
    }
}
