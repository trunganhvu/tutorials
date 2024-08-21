package IntStream;

import java.util.stream.IntStream;

/**
 * Convert to LongStream
 * @author AnhVT6
 *
 */
public class mapToLong {
    public static void main(String[] args) {

        IntStream.rangeClosed(1, 5)
				.mapToLong(n -> n)
		        .forEach(System.out::print);		// 12345
        
        System.out.println();
        
        IntStream.rangeClosed(1, 5)
        		.mapToLong(n -> n * 1000L)
                .forEach(System.out::print);		// 10002000300040005000
    }
}
