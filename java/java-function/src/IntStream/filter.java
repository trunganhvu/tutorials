package IntStream;

import java.util.stream.IntStream;

/**
 * Return elements are satisfy the condition
 */
public class filter {
    public static void main(String[] args) {
        // Lọc các số chẵn từ IntStream
        IntStream evenNumbers = IntStream.rangeClosed(1, 10)
                .filter(n -> n % 2 == 0);

        evenNumbers.forEach(System.out::print);     // 246810
    }
}
