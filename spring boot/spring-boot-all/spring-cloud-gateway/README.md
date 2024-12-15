# Gateway
## Dependencies
```xml
<properties>
    <spring-cloud.version>2024.0.0</spring-cloud.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>

    <!-- ....... -->
</dependencies>
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## Properties
> Đăng ký serviceservice
```yml
server:
  port: 8080
spring:
  application:
    name: gateway-service   # Tên gateway service
  cloud:
    gateway:
      routes:                   # Tạo routes cho gateway
        - id: demo-service      # Tên route demo
          uri: http://localhost:9090    # Uri cua service demo
          predicates:
            - Path=/demo-service/**     # Path cua service demo. Mapping với server.servlet.context-path của service demo
```

# Demo service
## Dependencies
> Không cần cài thêm gì

## Properties
```properties
spring.application.name=demo-service
# path of the application which registered in gateway service
server.servlet.context-path=/demo-service
server.port=9090
```


# Endpoint
## Endpoint Demo service directly demo service (8080)
```url
http://localhost:8080/demo-service/index
```

Response
```
Welcome to demo service
```

## Endpoint Demo service via gateway (9090)
```url
http://localhost:9090/demo-service/index
```

Response
```
Welcome to demo service
```