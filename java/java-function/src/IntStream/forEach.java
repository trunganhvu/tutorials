package IntStream;

import java.util.stream.IntStream;

public class forEach {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 10);

        intStream.forEach(System.out::print);		// 12345678910
    }
}
