package IntStream;

import java.util.stream.IntStream;

/**
 * Convert Stream to int array
 * @author AnhVT6
 *
 */
public class toArray {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 5);

        int[] array = intStream.toArray();	// convert to array

        for (int num : array) {
            System.out.print(num);	// 12345
        }
    }
}
