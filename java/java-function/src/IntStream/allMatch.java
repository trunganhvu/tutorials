package IntStream;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * Check all value in stream satisfy the condition of IntPredicate (Function interface)
 */
public class allMatch {
    public static void main(String[] args) {
        // IntStream ís simple traversal
        IntStream intStream = IntStream.rangeClosed(1, 10); // Create stream number 1 to 10
        IntPredicate isPositive = n -> n > 0; // functional interface using int input and return boolen
        boolean allPositive1 = intStream.allMatch(isPositive); // Check all value matching with intStream
        System.out.println("All value above 0: " + allPositive1); // true


        boolean allPositive2 = IntStream.rangeClosed(1, 10)
                .allMatch(n -> n > 0);
        System.out.println("All value above 0: " + allPositive2); // true


        IntStream intStream1 = IntStream.rangeClosed(1, 10); // Create stream number 1 to 10
        // Define an IntPredicate that checks if a number is prime.
        IntPredicate isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        boolean allPrime = intStream1.allMatch(isPrime);
        System.out.println("All value a number is prime: " + allPrime);     // false

    }
}
