# @SuppressWarnings 
là một annotation trong Java được sử dụng để ngăn trình biên dịch phát ra các cảnh báo đối với đoạn mã mà bạn cho là an toàn và không cần thiết phải sửa chữa. Điều này có thể hữu ích khi bạn biết rằng một đoạn mã có thể gây ra cảnh báo, nhưng nó không gây ra lỗi hoặc vấn đề về hiệu năng.
> DEMO: https://github.com/trunganhvu/trainer/tree/main/java/demo/suppress-warnings

### Cách sử dụng @SuppressWarnings
@SuppressWarnings có thể được áp dụng cho các mức độ khác nhau trong mã nguồn Java, bao gồm:

* Class: Để áp dụng cho toàn bộ class.
* Method: Để áp dụng cho một phương thức cụ thể.
* Field: Để áp dụng cho một trường (field) cụ thể.
* Constructor: Để áp dụng cho một hàm khởi tạo (constructor) cụ thể.
* Local variable: Để áp dụng cho một biến cục bộ.

## Cú pháp
```java
@SuppressWarnings("warning_name")
```
Trong đó, "warning_name" là một chuỗi mô tả loại cảnh báo mà bạn muốn tắt. Một số tên cảnh báo thông dụng bao gồm:

* **unchecked**: Tắt cảnh báo liên quan đến các thao tác không an toàn với các kiểu dữ liệu generic (ví dụ như khi sử dụng raw types).
* **deprecation**: Tắt cảnh báo liên quan đến việc sử dụng các phương thức hoặc lớp đã bị đánh dấu là deprecated (không còn được khuyến nghị sử dụng).
* **serial**: Tắt cảnh báo liên quan đến việc thiếu serialVersionUID trong các lớp thực hiện giao diện Serializable.
* **rawtypes**: Tắt cảnh báo liên quan đến việc sử dụng raw types (các kiểu generic mà không chỉ định tham số loại).

### @SuppressWarnings("unchecked")
```java
@SuppressWarnings("unchecked")
public void addElements(List rawList) {
    List<String> stringList = rawList;  // rawList maybe type of collection problems. Not warring
    stringList.add("Example");
}
```
### @SuppressWarnings("deprecation")
```java
@SuppressWarnings("deprecation")
public void useDeprecatedMethod() {
    DeprecatedClass deprecatedObject = new DeprecatedClass();                   // Not warring
    deprecatedObject.deprecatedMethod(); // Not warring
}
```

### @SuppressWarnings("rawtypes")

```java
@SuppressWarnings("rawtypes")
public void addToRawList() {
    List rawList = new ArrayList(); // Raw type, warning suppressed
    rawList.add("Hello");
    rawList.add(123);       // Not warring
}
```

### @SuppressWarnings("serial")
```java
@SuppressWarnings("serial")
public class Person implements Serializable {
    private String name;
    private int age;
}
```


 
