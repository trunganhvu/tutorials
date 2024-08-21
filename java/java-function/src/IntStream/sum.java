package IntStream;

import java.util.stream.IntStream;

/**
 * Get sum of stream
 * @author AnhVT6
 *
 */
public class sum {
    public static void main(String[] args) {
        int sum = IntStream.rangeClosed(1, 5)
                .sum();		
        System.out.println("Sum: " + sum);	// 15
    }
}
