# @SneakyThrows
Annotaion thay thế cho việc method định nghĩa checked exception

### Throw exception bình thường. 
```java
public void paypalPayment() throws IOException {
    System.out.println("bankPayment is running");
    throw new IOException("paypalPayment throw exception");    // Checked exception
}
```
Code sau khi compile không thay đổi hay tạo code mới
```java
public void paypalPayment() throws IOException {
    System.out.println("bankPayment is running");
    throw new IOException("paypalPayment throw exception");
}
```

### Sử dụng @SneakyThrows không phải throws IOException sau tên method
```java
@SneakyThrows
public void bankPayment() {
    System.out.println("bankPayment is running");
    throw new IOException("bankPayment throw exception");    // Checked exception
}
```

Code sau khi compile được tạo thêm với try catch và throw đói tượng Throwable
```java
public void bankPayment() {
    try {
        System.out.println("bankPayment is running");
        throw new IOException("bankPayment throw exception");
    } catch (Throwable var2) {
        Throwable $ex = var2;
        throw $ex;
    }
}
```