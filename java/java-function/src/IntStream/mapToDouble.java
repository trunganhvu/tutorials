package IntStream;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Convert to Double Stream
 * @author AnhVT6
 *
 */
public class mapToDouble {
    public static void main(String[] args) {
        DoubleStream doubleStreamDemo1 = IntStream.rangeClosed(1, 5).mapToDouble(n -> n * 1.5);
        doubleStreamDemo1.forEach(System.out::print);	// 1.5 3.0 4.5 6.0 7.5
        
        System.out.println();
        
        DoubleStream doubleStreamDemo2 = IntStream.rangeClosed(1, 5).mapToDouble(n -> n);
        doubleStreamDemo2.forEach(System.out::print);	// 1.0 2.0 3.0 4.0 5.0
    }
}
