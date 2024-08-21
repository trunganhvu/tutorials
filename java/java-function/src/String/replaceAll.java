package String;

public class replaceAll {
    public static void main(String[] args) {
        String str = "My phone number is 123-456-7890.";
        String newStr1 = str.replaceAll("\\d", "X");
        System.out.println(newStr1); // My phone number is XXX-XXX-XXXX.
        String newStr2 = str.replaceAll("\\s", "_");
        System.out.println(newStr2); // My_phone_number_is_123-456-7890.
    }
}
