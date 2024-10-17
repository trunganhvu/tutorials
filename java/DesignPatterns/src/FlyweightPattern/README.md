# Flyweight Design Pattern 
> là một mẫu thiết kế cấu trúc giúp giảm thiểu việc sử dụng bộ nhớ bằng cách chia sẻ đối tượng 
> thay vì tạo ra nhiều bản sao giống nhau. 

> Mẫu này rất hữu ích khi bạn có nhiều đối tượng tương tự và muốn tiết kiệm tài nguyên.

> String Pool và Database Connection Pool là hai ví dụ điển hình cho việc sử dụng mẫu này để tiết kiệm bộ nhớ và tối ưu hóa hiệu suất.

## Sử dụng Collection + Factory để lưu và sử dụng giá trị với tối ưu hiệu năng
```java
private HashMap<String, Flyweight> flyweights = new HashMap<>();

public Flyweight getFlyweight(String intrinsicState) {
    return flyweights.merge(intrinsicState,
            new ConcreteFlyweight(intrinsicState),
            (existing, newFlyweight) -> existing);
}
```


