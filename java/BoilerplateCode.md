# Boilerplate code
> Boilerplate code là những dòng code mà nhiều project luôn phải viết đi viết lại mà không có gì thay đổi. Từ này xuất phát từ việc quản lý tài liệu, bao gồm việc sử dụng lại các mẫu tài liệu hoặc boilerplate code và không cần thay đổi nhiều để phù hợp cho nhiều ngữ cảnh.

### 1. Getter và Setter
Các class có các fields đều sử dụng setter/getter nên có thể gọi là Boilerplate code.
Mỗi class Person hay bất cứ object nào thì đều dạng code như này
```java
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```
Tính năng record từ java 14 giảm boilerplate code setter/getter
```java
public record Person(String name, int age) {}
```

Giải pháp giảm Boilerplate code trong spring boot sử dụng lombok
```java
@Data
public class Person {
    private String name;
    private int age;
}
```

### 2. Triển khai Interface
Các class implement đôi khi không thay đổi 1 vài method từ interface
```java
public interface Printer {
    void print(String message);
}

public class ConsolePrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
```

Từ java 8 có thể sử dụng default method để định nghĩa giúp giảm boilerplate code
```java
public interface Printer {
    void print(String message);

    default void printError(String message) {       // Methods không cần class implement định nghĩa mà có thể dùng chung
        System.err.println("ERROR: " + message);
    }
}

public class ConsolePrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
```

Hoặc là sử dụng @Delegate trong thư viện lombok
```java
@Delegate
private final Printer printer = new PrinterImpl();
```

### 3. Khởi tạo đối tượng
Mỗi khi sử dụng DatabaseConnection thì cần truyền vào constructor full tham số-> Có thể khác phục bặng builder pattern/factory pattern hoặc chuyển qua record. Khi sử dụng có thể tối ưu bằng việc khai báo hoặc sử dụng DI
```java
public class DatabaseConnection {
    private String url;
    private String username;
    private String password;

    public DatabaseConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    // Phương thức kết nối và logic khác...
}
```

