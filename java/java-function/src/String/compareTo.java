package String;

public class compareTo {
    public static void main(String[] args) {
        String str1 = "apple";
        String str2 = "banana";

        // The comparison is based on the Unicode value of each character in the strings
        int result1 = "str1".compareTo("str2");
        System.out.println("result1: " + result1); // -1 (mean str1 < str2)

        int result2 = "str1".compareTo("str1");
        System.out.println("result2: " + result2); // 0 (mean str1 = str2)

        int result3 = "abc".compareTo("abcdeasds");
        System.out.println("result3: " + result3); // -6 (mean abc = abc AND len(abc) - len(abcdeasds) = -6)

        int result4 = "abc".compareTo("ab");
        System.out.println("result4: " + result4); // 1 (mean ab = ab AND len(abc) - len(ab) = 1)

        int result5 = "xy".compareTo("ab");
        System.out.println("result5: " + result5); // 23 (mean code_point(x) - code_point(a) = 23)
    }
}
