package String;

import java.util.Locale;

public class format {
    public static void main(String[] args) {
        String name = "Alice";
        int age = 30;
        double height = 1.65;

        // static String format(String format, Object... args)
        String formattedString = String.format("Name: %s, Age: %d, Height: %.2f meters", name, age, height);    // Name: Alice, Age: 30, Height: 1,65 meters
        System.out.println("Formatted string: " + formattedString);

        // static String format(Locale l, String format, Object... args)
        String formattedWithLocale = String.format(Locale.FRANCE, "Name: %s, Age: %d, Height: %.2f meters", name, age, height); // Name: Alice, Age: 30, Height: 1,65 meters
        System.out.println("Formatted string with locale: " + formattedWithLocale);
    }
}
