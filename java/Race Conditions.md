# Race conditions (Tình huống tương tranh)
> Khi có hai hay nhiều Thread cùng chia sẻ dữ liệu, hay đơn giản là cùng đọc và ghi vào một vùng dữ liệu

Code gây ra Race Conditions gọi là Critical sections (mã găng)
```java
public class Counter {
    protected long count = 0;

    public void add(long value) {
        this.count += value;
    }
}
```

## Solution 1 sử dụng synchronized để đồng bộ
```java
public class Counter {
    protected long count = 0;

    public void add(long value) {
        synchronized(this) {
            this.count += value;
        }
    }
}
```

## Solution 2: Thread Safety & Shared Resources

### Local Variables
```java
public void someMethod() {
    long threadSafeInt = 0;
    threadSafeInt++;
}
```
### Local Object References
> Không có biến/đối tượng nào được truyền sang thread khác thread hiện tại
```java
public void someMethod() {
    LocalObject localObject = new LocalObject();

    localObject.callMethod();
    method2(localObject);
}

public void method2(LocalObject localObject) {
    localObject.setValue("value");
}
```
### Object Member Variables 
```java
public class NotThreadSafe {
    StringBuilder builder = new StringBuilder();

    public add(String text) {
        this.builder.append(text);
    }
}

public class MyRunnable implements Runnable {
    NotThreadSafe instance = null;

    public MyRunnable(NotThreadSafe instance) {
        this.instance = instance;
    }

    public void run() {
        this.instance.add("some text");
    }
}
```
Race conditions occurred
```java
NotThreadSafe sharedInstance = new NotThreadSafe();

new Thread(new MyRunnable(sharedInstance)).start();
new Thread(new MyRunnable(sharedInstance)).start();
```

Thread Safety
```java
new Thread(new MyRunnable(new NotThreadSafe())).start();
new Thread(new MyRunnable(new NotThreadSafe())).start();
```

### Thread Control Escape Rule
>  nếu thread 1 và thread 2 cùng create database connections cho riêng nó (connection 1 và connection 2), thì bản thân mỗi connection được coi là thread safe. 
Nhưng việc sử dụng database connections không được coi là thread safe.

> Với trường hợp check null trước khi khởi tạo thì trường hợp có con & transaction đang thực hiện thì thread hiện tại sẽ phải waiting
=> Không nên có thêm điều kiện check con == null trước khi khởi tạo con

## Solution 3: Thread Safety & Immutability
### Class Immutability
không sử dụng setter
```java
public class ImmutableValue{

    private int value = 0;

    public ImmutableValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
```

Tạo đối tượng mới mỗi khi update fields
```java
public class ImmutableValue{

    private int value = 0;

    public ImmutableValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public ImmutableValue add(int valueToAdd){
        return new ImmutableValue (this.value + valueToAdd);
    }

}
```

