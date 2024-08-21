package String;

public class codePointCount {
    public static void main(String[] args) {


        // Count distinct code point in string from beginIndex (include) to endIndex (not include: endIndex - 1)
        // Code point is integer of represent a unicode character
        int count1 = "Hello 😊 h".codePointCount(0, 6);
        System.out.println("Number of code points between positions 0 and 6: " + count1);   // 6 (H e l o 😊)

        int count2 = "Hello 😊 h".codePointCount(0, 2);
        System.out.println("Number of code points between positions 0 and 2: " + count2);   // 2

        int count3 = "Hello 😊 h".codePointCount(0, 1);
        System.out.println("Number of code points between positions 0 and 1: " + count3);   // 1

    }
}
