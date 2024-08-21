package String;

public class indent {
    public static void main(String[] args) {
        String text1 = "Hello\nWorld\nJava";
        String text2 = "Hello\r\nWorld\r\nJava";
        String text3 = "Hello\tWorld\tJava";

        // Add in space into head of line (find by \n or\r\n)
        String indentedText1 = text1.indent(2);
        String indentedText2 = text2.indent(2);
        String indentedText3 = text3.indent(10);

        System.out.println(indentedText1);  //   Hello...
        System.out.println(indentedText2);  //   Hello...
        System.out.println(indentedText3);  //           Hello	World	Java
    }
}
