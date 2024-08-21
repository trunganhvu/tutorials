package IntStream;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Find the first element in stream
 */
public class findFirst {
    public static void main(String[] args) {

        // Get first element IntStream
        OptionalInt firstElement = IntStream.rangeClosed(1, 100).findFirst();

        if (firstElement.isPresent()) {             // true
            System.out.println("Element: " + firstElement.getAsInt()); // 1
        } else {
            System.out.println("Not found element.");

        }

        // Get first element IntStream
        OptionalInt firstElement1 = IntStream
                .rangeClosed(1, 100)
                .filter(n -> n % 20 == 0)       // filter element % 20 = 0
                .findFirst();                   // get first

        if (firstElement1.isPresent()) {             // true
            System.out.println("Element: " + firstElement1.getAsInt());    // 20
        } else {
            System.out.println("Not found element % 20 = 0.");
        }

        // Get first element IntStream
        OptionalInt firstElement2 = IntStream
                .rangeClosed(1, 100)
                .filter(n -> n % 120 == 0)       // filter element % 120 = 0
                .findFirst();                   // get first

        if (firstElement2.isPresent()) {             // false
            System.out.println("Element: " + firstElement2.getAsInt());
        } else {
            System.out.println("Not found element % 120 = 0.");
        }
    }
}
