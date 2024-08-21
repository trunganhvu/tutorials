package String;

public class compareToIgnoreCase {
    public static void main(String[] args) {

        // Like as compareTo without ignore upper case charactor
        // The comparison is based on the Unicode value of each character in the strings
        int result1 = "str1".compareToIgnoreCase("sTr2");
        System.out.println("result1: " + result1); // -1 (mean str1 < str2)

        int result2 = "stR1".compareToIgnoreCase("str1");
        System.out.println("result2: " + result2); // 0 (mean str1 = str2)

        int result3 = "aBc".compareToIgnoreCase("abcdeasds");
        System.out.println("result3: " + result3); // -6 (mean abc = abc AND len(abc) - len(abcdeasds) = -6)

        int result4 = "abc".compareToIgnoreCase("ab");
        System.out.println("result4: " + result4); // 1 (mean ab = ab AND len(abc) - len(ab) = 1)

        int result5 = "xy".compareToIgnoreCase("ab");
        System.out.println("result5: " + result5); // 23 (mean code_point(x) - code_point(a) = 23)
    }
}
