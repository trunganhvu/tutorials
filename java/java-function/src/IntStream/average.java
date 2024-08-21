package IntStream;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

/**
 * Get average in IntStream
 * Return value type OptionalDouble
 */
public class average {
    public static void main(String[] args) {
        // IntStream ís simple traversal
        IntStream intStream = IntStream.rangeClosed(1, 10);
        OptionalDouble average1 = intStream.average();                // Get average
        System.out.println("average1: " + average1.getAsDouble());    // 5.5

        OptionalDouble average2 = IntStream.rangeClosed(1, 10)
                .asLongStream() // Convert to LongStream
                .average();     // Get average
        System.out.println("average2: " + average2.getAsDouble());    // 5.5

    }
}
