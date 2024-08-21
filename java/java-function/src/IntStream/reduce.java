package IntStream;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Reduction all element in stream and return a value of function action
 * @author AnhVT6
 *
 */
public class reduce {
    public static void main(String[] args) {
        // Set first value is 0 and doing method Integer.sum() every element
        IntStream intStream = IntStream.rangeClosed(1, 5);
        int sum = intStream.reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);		// 15


        // Set first value is MAX_VALUE and doing method Integer.min()
        IntStream intStream1 = IntStream.of(5, 3, 8, 1, 9);
        int min = intStream1.reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println("Min: " + min);		// 1


     // Doing method Integer.sum() every element without first value => return optional
        IntStream intStream2 = IntStream.rangeClosed(1, 5);
        OptionalInt sumOptional = intStream2.reduce(Integer::sum);

        if (sumOptional.isPresent()) {		// true
            System.out.println("Sum: " + sumOptional.getAsInt());		// 15
        } else {
            System.out.println("Stream is empty.");
        }

    }
}
