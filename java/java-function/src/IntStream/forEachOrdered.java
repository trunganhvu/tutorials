package IntStream;

import java.util.stream.IntStream;

public class forEachOrdered {
    public static void main(String[] args) {
        
        // Sequence of iterator over every element of the stream is not of the parallel streams
        IntStream.of(4,5,7,3,2,1,9,8,6,10).forEach(System.out::print);		// 45732198610
        System.out.println();

        // Random of iterator over every element of the stream belonging to parallel streams
        IntStream.of(4,5,7,3,2,1,9,8,6,10).parallel().forEach(System.out::print);	// 91810463527
        System.out.println();

        // Sequence of iterator over every element of the stream belonging to parallel streams
        IntStream.of(4,5,7,3,2,1,9,8,6,10).parallel().forEachOrdered(System.out::print);	// 45732198610
        System.out.println();
    }
}
