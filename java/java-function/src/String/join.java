package String;

public class join {
    public static void main(String[] args) {
        String fruit1 = "Apple";
        String fruit2 = "Banana";
        String fruit3 = "Orange";

        // Join all value have a delimiter
        String result = String.join(", ", fruit1, fruit2, fruit3);
        System.out.println(result);         // Apple, Banana, Orange


        String[] fruits = {"Apple", "Banana", "Orange"};
        String result1 = String.join(", ", fruits);
        System.out.println(result1);        // Apple, Banana, Orange
    }
}
