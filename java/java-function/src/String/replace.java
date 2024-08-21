package String;

public class replace {
    public static void main(String[] args) {
        String str = "hello world";
        String newStr1 = str.replace('o', 'a');
        System.out.println(newStr1); // "hella warld"

        String newStr2 = str.replace("world", "Java");
        System.out.println(newStr2); // "hello Java"
    }
}
