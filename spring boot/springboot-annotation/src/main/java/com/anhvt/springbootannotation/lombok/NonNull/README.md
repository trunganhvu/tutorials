# @NonNull
Trong thư viện lombok.NonNull như bước validate parameter của method. Yêu cầu khác null nếu không sẽ throw exception

Sử dụng trong phương thức
```java
public void println(@NonNull int id, @NonNull String name) {
    System.out.println("id: " + id + " - name: " + name);
}
```

Kết quả
```java
Page page = new Page();
page.println(1, "11");  // id: 1 - name: 11
page.println(2, null);  // NullPointerException: name is marked non-null but is null
```

Method khi được compile
```java
public void println(@NonNull int id, @NonNull String name) {
    if (name == null) {
        throw new NullPointerException("name is marked non-null but is null");
    } else {
        System.out.println("id: " + id + " - name: " + name);
    }
}
```