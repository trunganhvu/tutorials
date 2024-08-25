# REACTOR-CORE IN JAVA

# Mono

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