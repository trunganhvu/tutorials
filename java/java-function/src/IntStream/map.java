package IntStream;

import java.util.stream.IntStream;

/**
 * Do something per every element 
 * stream value1 -> stream value2
 * @author AnhVT6
 *
 */
public class map {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 5)
                .map(n -> n * 2);

        intStream.forEach(System.out::print);		// 246810
    }
}
