package IntStream;

import java.util.stream.IntStream;

/**
 * Return true if no element satisfy the condition of functional interface
 * Otherwise return false 
 * @author AnhVT6
 *
 */
public class noneMatch {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 10);

        boolean noneNegative = intStream.noneMatch(n -> n < 0);

        if (noneNegative) {
            System.out.println("Don't have any negative number.");
        } else {
            System.out.println("Exist a nagative number.");
        }
    }
}
