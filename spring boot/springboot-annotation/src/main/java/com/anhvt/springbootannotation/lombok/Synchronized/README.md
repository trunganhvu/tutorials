# @Synchronized
annotation tự động đồng bộ hóa (synchronize) các methods hoặc các khối mã để đảm bảo rằng chỉ có một luồng (thread) có thể truy cập vào khối hoặc method đó tại một thời điểm

### Khi sử dụng annotation @synchronized và keyword synchronized

```java
public synchronized void increment1(int index) {
    count++;
    System.out.println("Index: " + index);
}

@Synchronized
public void increment2(int index) {
    count++;
    System.out.println("Index: " + index);
}
```

Code sau khi compile @synchronized sẽ compile theo block
```java
public synchronized void increment1(int index) {
    ++this.count;
    System.out.println("Index: " + index);
}

public void increment2(int index) {
    synchronized(this.$lock) {
        ++this.count;
        System.out.println("Index: " + index);
    }
}
```