package String;

public class replaceFirst {
    public static void main(String[] args) {
        String str = "My phone numbers are 123-456-7890 and 987-654-3210.";
        String newStr = str.replaceFirst("\\d{4}", "XXXX");
        System.out.println(newStr); // My phone numbers are 123-456-XXXX and 987-654-3210.

    }
}
