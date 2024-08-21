package IntStream;

import java.util.stream.IntStream;

/**
 * Create rule to make a stream where all elements satisfy the conditions
 * @author AnhVT6
 *
 */
public class iterate {
    public static void main(String[] args) {
        // Create unlimited stream of elements start from 0 to infinitely
        IntStream naturalNumbers = IntStream.iterate(0, n -> n + 1);

        // print limit 10 elements
        naturalNumbers.limit(10).forEach(System.out::print);		// 0123456789
        

        System.out.println("\n");


        // static IntStream iterate(int seed, IntPredicate hasNext, IntUnaryOperator next)
        // Create stream of element start 0 to 10 (condition < 10) and step 2
        IntStream evenNumbers = IntStream.iterate(
                0,         		// start value
                n -> n < 10,    // condition of value
                n -> n + 2      // Functional interface to increase by 2
        );

        evenNumbers.forEach(System.out::print);		// 02468
    }
}
