# @Delegate
sử dụng để tạo ra các phương thức từ một đối tượng khác (delegate object) và tự động thêm chúng vào lớp hiện tại. Điều này giúp giảm boilerplate code khi bạn muốn chia sẻ hoặc tái sử dụng các phương thức từ một lớp khác mà không cần thừa kế từ lớp đó.

### Cách sử dụng
**Định nghĩa interface và class implement**
```java
public interface Printer {
    void print(String message);
}

public class PrinterImpl implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
```

**Định nghĩa class sử dụng Delegate**
```java
public class Document {
    @Delegate
    private final Printer printer = new PrinterImpl();
}
```

**Khi sử dụng đối tượng Document thì có sẵn method print() của class implement
```java
Document document = new Document();
        document.print("Hello, World!"); // This will call printer.print("Hello, World!")
```

### Class Document được compile
```java
public class Document {
    private final Printer printer = new PrinterImpl();

    public Document() {
    }

    @Generated
    public void print(final String message) {
        this.printer.print(message);
    }
}
```