package IntStream;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Find max element in stream
 * @author AnhVT6
 *
 */
public class max {
    public static void main(String[] args) {
        OptionalInt intStream = IntStream.rangeClosed(1, 10)
                .max();	// Find max value in [1, 10]

        if (intStream.isPresent()) {	// true
            System.out.println("Max: " + intStream.getAsInt());	// 10
        } else {
            System.out.println("Stream is empty");
        }
    }
}
