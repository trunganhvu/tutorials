package liskovsubstitution;

public class MainLiskov {
    public static void main(String[] args) {
        // Link: https://toidicodedao.com/2016/05/17/series-solid-cho-thanh-nien-code-cung-liskov-substitution-principle/
        // Rectangle (width, height, getArea())
        // Square extend Rectangle (override set width: width=height, set height: width=height)
        // Bad => Field in Square change attribute of field in Rectangle


        // Rectangle (width, height) and Square(side) extends Shape(getArea())
        // Good => Children class not change target attribute of parent class


        // Solution: Create interface/abstract class have some common methods
        // Child class define detail attributes and extend only common methods of parent

    }
}
