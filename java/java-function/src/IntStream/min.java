package IntStream;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Find min element in stream
 * @author AnhVT6
 *
 */
public class min {
    public static void main(String[] args) {
        OptionalInt intStream = IntStream.rangeClosed(1, 10)
                .min();

        if (intStream.isPresent()) {	// true
            System.out.println("Min: " + intStream.getAsInt());	// 1
        } else {
            System.out.println("Stream is empty");
        }
    }
}
