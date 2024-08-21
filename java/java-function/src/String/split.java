package String;

public class split {
    public static void main(String[] args) {
        String str = "apple banana cherry";

        // Split add item by delimiter
        String[] parts1 = str.split(" ");
        for (String part : parts1) {
            System.out.println(part);
        }
        // apple
        // banana
        // cherry

        // =============
        // Split add item by delimiter
        String[] parts2 = str.split(" ", 2);
        for (String part : parts2) {
            System.out.println(part);
        }
        // apple
        // banana cherry
    }
}
