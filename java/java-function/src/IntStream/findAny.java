package IntStream;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Return any element in results is satisfy the condition
 */
public class findAny {
    public static void main(String[] args) {

        // Find a number % 15 = 0
        OptionalInt divisibleBy15 = IntStream.rangeClosed(1, 100).filter(n -> n % 15 == 0).findAny();

        if (divisibleBy15.isPresent()) {             // true
            System.out.println("Number: " + divisibleBy15.getAsInt());  // 15
        } else {
            System.out.println("Don't exist number % 15 = 0.");
        }


        // Find a number % 115 = 0
        OptionalInt divisibleBy115 = IntStream.rangeClosed(1, 100).filter(n -> n % 115 == 0).findAny();

        if (divisibleBy115.isPresent()) {           // false
            System.out.println("Number: " + divisibleBy115.getAsInt());
        } else {
            System.out.println("Don't exist number % 115 = 0.");
        }
    }
}
