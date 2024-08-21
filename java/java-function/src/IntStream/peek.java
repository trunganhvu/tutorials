package IntStream;

import java.util.stream.IntStream;

/**
 * Return value not remove element in stream 
 * @author AnhVT6
 *
 */
public class peek {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 5);

        intStream.peek(x -> System.out.print(" [" + x + "] "))	// Using peek value
                .map(n -> n * 2)                // Action make new stream
                .forEach(System.out::print);	
        // result: [1] 2 [2] 4 [3] 6 [4] 8 [5] 10
    }
}
