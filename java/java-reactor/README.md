# REACTOR-CORE IN JAVA
> Reactor là phát triển các ứng dụng không đồng bộ và không chặn ở dạng hướng sự kiện

Publisher là nơi phát ra/emit dữ liệu bao gồm Mono và Flux. Mono và Flux đều là cách triển khai giao diện Nhà xuất bản. Nói một cách đơn giản, chúng ta có thể nói rằng khi chúng ta thực hiện một việc gì đó như tính toán hoặc đưa ra yêu cầu đối với cơ sở dữ liệu hoặc dịch vụ bên ngoài và mong đợi tối đa một kết quả thì chúng ta nên sử dụng Mono. Khi mong đợi nhiều kết quả từ quá trình tính toán, cơ sở dữ liệu hoặc lệnh gọi dịch vụ bên ngoài, thì chúng ta nên sử dụng Flux
Mono và Flux đề kế thừa từ Publisher và được implement qua CorePublisher

Mỗi publisher có method subscribe() để thực hiện việc Subscriber -> Đón dữ liệu

Publisher
```java
package org.reactivestreams;

public interface Publisher<T> {
    void subscribe(Subscriber<? super T> var1);
}
```

Mono
```java
public abstract class Mono<T> implements CorePublisher<T> {}
```

Flux
```java
public abstract class Flux<T> implements CorePublisher<T> {}
```

CorePublisher
```java
public interface CorePublisher<T> extends Publisher<T> {
    void subscribe(CoreSubscriber<? super T> var1);
}
```

Vòng đời sự kiện
![Reactive Programming](/images/Spring%20WebFlux%20-%20Reactive%20Programming.001.jpeg)


Subscriber
```java
package org.reactivestreams;

public interface Subscriber<T> {
    void onSubscribe(Subscription var1);

    void onNext(T var1);

    void onError(Throwable var1);

    void onComplete();
}
```

Subscription
```java
package org.reactivestreams;

public interface Subscription {
    void request(long var1);

    void cancel();
}
```

Processor
```java
package org.reactivestreams;

public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}
```

# MonoReturns 0 or 1 element.
> The Mono chỉ producing duy nhất 1 value.

Cụ thể các methods tham khảo mono/
### block
là method chặn việc run tiếp thread để chờ xử lý (như code đồng bộ)
Ví dụ trong việc methods block để chờ repository query  
```java
public User getUserById(String userId) {
    return userRepository.findById(userId)
                         .block();  // Chặn cho đến khi user được truy xuất
}
```
Ví dụ trong việc methods block để chờ kết quả từ api
```java
public String getThirdPartyData() {
    WebClient webClient = WebClient.create("https://api.example.com");
    return webClient.get()
                    .uri("/data")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();  // Chặn cho đến khi phản hồi được truy xuất
}

```

# Flux: Returns 0…N elements.
> Flux có thể produce nhiều value.

Các methods flux có phần giống với mono. Cụ thể tham khảo flux/