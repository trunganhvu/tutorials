# Monitor health Actuator
> Spring boot actuator Trong bài viết này, mình sẽ giới thiệu về Spring Boot Actuator. Là một công cụ trong framwork Spring, cho phép chúng ta giám sát ứng dụng của mình trên môi trường sản xuất mà không cần phải viết code. Nó cung cấp thông tin về ứng dụng, trạng thái của ứng dụng, các dữ liệu liên quan đến ứng dụng.

# Dependency
```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

# URL
```shell
http://localhost:8080/actuator
```

Response
```shell
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/actuator",
            "templated": false
        },
        "beans": {
            "href": "http://localhost:8080/actuator/beans",
            "templated": false
        },
        "caches-cache": {
            "href": "http://localhost:8080/actuator/caches/{cache}",
            "templated": true
        },
        "caches": {
            "href": "http://localhost:8080/actuator/caches",
            "templated": false
        },
        "health-path": {
            "href": "http://localhost:8080/actuator/health/{*path}",
            "templated": true
        },
        "health": {
            "href": "http://localhost:8080/actuator/health",
            "templated": false
        },
        "info": {
            "href": "http://localhost:8080/actuator/info",
            "templated": false
        },
        "conditions": {
            "href": "http://localhost:8080/actuator/conditions",
            "templated": false
        },
        "configprops": {
            "href": "http://localhost:8080/actuator/configprops",
            "templated": false
        },
        "configprops-prefix": {
            "href": "http://localhost:8080/actuator/configprops/{prefix}",
            "templated": true
        },
        "env": {
            "href": "http://localhost:8080/actuator/env",
            "templated": false
        },
        "env-toMatch": {
            "href": "http://localhost:8080/actuator/env/{toMatch}",
            "templated": true
        },
        "loggers": {
            "href": "http://localhost:8080/actuator/loggers",
            "templated": false
        },
        "loggers-name": {
            "href": "http://localhost:8080/actuator/loggers/{name}",
            "templated": true
        },
        "heapdump": {
            "href": "http://localhost:8080/actuator/heapdump",
            "templated": false
        },
        "threaddump": {
            "href": "http://localhost:8080/actuator/threaddump",
            "templated": false
        },
        "metrics-requiredMetricName": {
            "href": "http://localhost:8080/actuator/metrics/{requiredMetricName}",
            "templated": true
        },
        "metrics": {
            "href": "http://localhost:8080/actuator/metrics",
            "templated": false
        },
        "scheduledtasks": {
            "href": "http://localhost:8080/actuator/scheduledtasks",
            "templated": false
        },
        "mappings": {
            "href": "http://localhost:8080/actuator/mappings",
            "templated": false
        }
    }
}
```


# API “/health”
```shell
http://localhost:8080/actuator/health
```

Response
```shell
{
    "status": "DOWN",
    "components": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 69794263040,
                "free": 25967710208,
                "threshold": 10485760,
                "path": "D:\\DEV\\tutorials\\spring boot\\spring-boot-all\\spring-boot-actuator\\.",
                "exists": true
            }
        },
        "ping": {
            "status": "UP"
        },
        "redis": {
            "status": "DOWN",
            "details": {
                "error": "org.springframework.data.redis.RedisConnectionFailureException: Unable to connect to Redis"
            }
        }
    }
}
```