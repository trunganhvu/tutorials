# Dependency
Thư viện spring web đã bao gồm Webclient, add thêm webflux để xử lý mono/flux
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

# Config
Bean định nghĩa webclient build với url api
```java
@Bean(name = "webClient")
public WebClient webClientBuilder() {
    String addressBaseUrl = "http://localhost:8080";
    return WebClient.builder().baseUrl(addressBaseUrl).build();
}
```

# Sử dụng webclient
Khai báo WebClient
```java
@Autowired
@Qualifier("webClient")
private WebClient webClient;
// TODO
```

Webclient 
* call api /client
* handler status (4xx, 5xx) and retry
* return success response or error response
```java
public Mono<ApiResponse> get() {
    return webClient.get()
            .uri("/client")
            .retrieve()
            .onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), response ->
                    response.bodyToMono(ApiResponse.class)
                            .flatMap(errorBody -> {
                                return Mono.error(new Exception("Client Error (4xx): " + errorBody));
                            })
            )
            .onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), response ->
                    response.bodyToMono(ApiResponse.class)
                            .flatMap(errorBody -> {
                                return Mono.error(new Exception("Server Error (5xx): " + errorBody));
                            })
            )
            .bodyToMono(ApiResponse.class)
            .retry(9)
            .onErrorResume(e -> {
                return Mono.just(new ApiResponse(500, "Error occurred: " + e.getMessage()));
            });
}
```