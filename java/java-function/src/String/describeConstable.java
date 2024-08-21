package String;

import java.util.Optional;

public class describeConstable {
    public static void main(String[] args) {
        String str = new String("Hello, Java!");
        // The method return value setted in Optional
        Optional<String> description = str.describeConstable();

        if(description.isPresent()) {
            System.out.println("Constant value: " + description.get()); // Usually is true
        } else {
            System.out.println("Not a constant value.");
        }

        String constantString1 = "constantString1";
        String constantString2 = "constantString2";
        String constantString3 = "";

        Optional<String> optionalConstant1 = constantString1.describeConstable();
        Optional<String> optionalConstant2 = constantString2.describeConstable();
        Optional<String> optionalConstant3 = constantString3.describeConstable();

        System.out.println("Optional constant1: " + optionalConstant1.get());   // constantString1
        System.out.println("Optional constant2: " + optionalConstant2.get());   // constantString2
        System.out.println("Optional constant3: " + optionalConstant3.get());   // blank
        optionalConstant1.ifPresent(System.out::println);   // constantString1
        optionalConstant3.ifPresent(System.out::println);   // blank
        optionalConstant2.ifPresent(System.out::println);   // constantString2

        System.out.println(optionalConstant1.isPresent());  // true
        System.out.println(optionalConstant2.isPresent());  // true
        System.out.println(optionalConstant3.isPresent());  // true

    }
}
