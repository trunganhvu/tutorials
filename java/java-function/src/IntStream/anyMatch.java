package IntStream;

import java.util.stream.IntStream;

/**
 * Check any value in stream satisfy the condition of IntPredicate (Function interface)
 */
public class anyMatch {
    public static void main(String[] args) {
        // IntStream ís simple traversal
        // Check any value in stream satisfy the even number
        boolean check = IntStream.rangeClosed(1, 10)
                .anyMatch(n -> n % 2 == 0);

        System.out.println("Have any even number: " + check);   // true

    }
}
