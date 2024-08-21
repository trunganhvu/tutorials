package String;

public class repeat {
    public static void main(String[] args) {
        String original = "Java ";

        // Repeat 3 time string
        String repeated = original.repeat(3);
        System.out.println(repeated); // Java Java Java

        // When repeat 0 time, result will be blank
        String empty = original.repeat(0);
        System.out.println(empty); // Blank
        System.out.println(empty.isBlank()); // true

        // Exception occurs when repeat time is negative number
        try {
            String invalid = original.repeat(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage()); // Output: String repeat count cannot be negative
        }
    }
}
