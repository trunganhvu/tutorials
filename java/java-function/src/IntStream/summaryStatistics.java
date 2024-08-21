package IntStream;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

/**
 * Calculator statistic basic as sum, average, min, max, count
 * @author AnhVT6
 *
 */
public class summaryStatistics {
	public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 10);

        IntSummaryStatistics stats = intStream.summaryStatistics();

        // In ra các thống kê mô tả
        System.out.println("Sum: " + stats.getSum());			// 55
        System.out.println("Average: " + stats.getAverage());	// 5.5
        System.out.println("Count: " + stats.getCount());		// 10
        System.out.println("Min: " + stats.getMin());			// 1
        System.out.println("Max: " + stats.getMax());			// 10
    }
}
