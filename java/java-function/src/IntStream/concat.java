package IntStream;

import java.util.stream.IntStream;


public class concat {
    public static void main(String[] args) {
        // Create 2 IntStream
        IntStream intStream1 = IntStream.rangeClosed(1, 5);
        IntStream intStream2 = IntStream.rangeClosed(6, 10);

        // new stream = stream 1 + stream 2
        IntStream concatenatedStream1 = IntStream.concat(intStream1, intStream2);
        concatenatedStream1.forEach(System.out::print);  // 12345678910

        System.out.println();
        // IntStream ís simple traversal
        IntStream concatenatedStream2 = IntStream.concat(IntStream.rangeClosed(6, 10), IntStream.rangeClosed(1, 5));
        concatenatedStream2.forEach(System.out::print);  // 67891012345
    }
}
