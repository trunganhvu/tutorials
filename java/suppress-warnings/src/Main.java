import java.util.ArrayList;
import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 8/19/2024
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ExecuteClass executeClass = new ExecuteClass();
        executeClass.print();
    }

    @SuppressWarnings("")
    public void addElements(List rawList) {
        List<String> stringList = rawList;  // rawList maybe type of collection problems
        stringList.add("Example");
    }

    @SuppressWarnings("deprecation")
    public void useDeprecatedMethod() {
        DeprecatedClass deprecatedObject = new DeprecatedClass();
        deprecatedObject.deprecatedMethod();
    }

    @SuppressWarnings("rawtypes")
    public void addToRawList() {
        List rawList = new ArrayList(); // Raw type, warning suppressed
        rawList.add("Hello");
        rawList.add(123);
    }

}