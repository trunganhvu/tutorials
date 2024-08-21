package IntStream;

import java.util.stream.IntStream;

/**
 * Take elements from the begin of stream until not satisfy the condition  
 * @author AnhVT6
 *
 */
public class takeWhile {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 10);

        intStream.takeWhile(n -> n < 5)		// 5 elements first satify the condition
        			.forEach(System.out::print);	// 1234
        
        System.out.println();
        IntStream intStream2 = IntStream.of(4,6,2,1,5,3,8,7);

        intStream2.takeWhile(n -> n < 5)	// 1 element first satisfy the condition
        		.forEach(System.out::print);	// 4
    }
}
