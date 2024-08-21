package String;

import java.util.HashMap;
import java.util.Map;

public class intern {
    private static final Map<String, String> nameToEmailMap = new HashMap<>();

    public static void addUser(String name, String email) {
        String internedName = name.intern(); // Internalization for name
        String internedEmail = email.intern(); // Internalization for email

        nameToEmailMap.put(internedName, internedEmail);
    }

    public static String getEmail(String name) {
        return nameToEmailMap.get(name.intern()); // Get email internalized
    }


    public static void main(String[] args) {
        // intern() return value exist in String Constant Pool, otherwise insert to SCP
        // String usually save address of string in SCP -> 2 String have the same content -> useful to reduce the memory
        String str1 = new String("Hello").intern();
        String str2 = new String("Hello").intern();
        String str3 = new String("Hello");
        String str4 = new String("Hello");


        System.out.println("str1 == str2: " + (str1 == str2));                      // true
        System.out.println("str1.equals(str2): " + (str1.equals(str2)));            // true
        System.out.println("str3 == str4: " + (str3 == str4));                      // false
        System.out.println("str3 == str4: " + (str1 == str4));                      // false
        System.out.println("str3.equals(str4): " + (str3.equals(str4)));            // true

        // EX:
        addUser("Alice", "alice@example.com");
        addUser("Bob", "bob@example.com");
        addUser("Alice", "anotheralice@example.com");

        System.out.println("Email of Alice: " + getEmail("Alice")); // "anotheralice@example.com"
        System.out.println("Email of Bob: " + getEmail("Bob")); // "bob@example.com"

    }
}
