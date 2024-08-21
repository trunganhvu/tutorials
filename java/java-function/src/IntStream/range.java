package IntStream;

import java.util.stream.IntStream;

/**
 * Defind stream with value in range [start, end)
 * @author AnhVT6
 *
 */
public class range {
    public static void main(String[] args) {
        IntStream intStream = IntStream.range(1, 6);
        intStream.forEach(System.out::print);	// 12345
    }	
}
