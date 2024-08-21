package String;

public class formatted {
    public static void main(String[] args) {
        String name = "Alice";
        int age = 30;
        double height = 1.65;

        // String formatted(Object... args)
        String formattedString = "Name: %s, Age: %d, Height: %.2f meters".formatted(name, age, height); // Name: Alice, Age: 30, Height: 1,65 meters
        System.out.println("Formatted string: " + formattedString);
    }
}
