# Information
### logback.xml
Cấu hình logging info, error và console

## LoggerFactory vs Slf4j
2 kiểu logging tương tự nhau đều có thể logging ở console và lưu trong files

Logging 
```shell
2024-09-15 18:03:33.431 [http-nio-8080-exec-2] INFO  c.a.s.controller.EmployeeController - START ==> LOG 1 : Inside EmployeeController getEmployee method.
2024-09-15 18:03:33.431 [http-nio-8080-exec-2] ERROR c.a.s.controller.EmployeeController - getEmployee error
2024-09-15 18:03:33.435 [http-nio-8080-exec-2] INFO  c.a.s.service.EmployeeService - START ==> LOG 1 : Inside EmployeeService getAllEmployee method.
2024-09-15 18:03:37.462 [http-nio-8080-exec-4] INFO  c.a.s.controller.EmployeeController - START ==> LOG 1 : Inside EmployeeController getAllEmployee method.
2024-09-15 18:03:37.462 [http-nio-8080-exec-4] INFO  c.a.s.controller.EmployeeController - START ==> LOG 2 : Inside EmployeeController getAllEmployee method.
2024-09-15 18:03:37.462 [http-nio-8080-exec-4] WARN  c.a.s.controller.EmployeeController - START ==> LOG 4 : Inside EmployeeController getAllEmployee method.
2024-09-15 18:03:37.462 [http-nio-8080-exec-4] INFO  c.a.s.service.EmployeeService - START ==> LOG 1 : Inside EmployeeService getAllEmployee method.
```
### LoggerFactory
Dependency
```shell
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.16</version>
</dependency>
```

Khai báo
```java
private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

LOGGER.info("START ==> LOG 1 : Inside EmployeeController getAllEmployee method.");
```

### @Slf4j
```java
log.info("START ==> LOG 2 : Inside EmployeeController getAllEmployee method.");
log.debug("START ==> LOG 3 : Inside EmployeeController getAllEmployee method.");
log.warn("START ==> LOG 4 : Inside EmployeeController getAllEmployee method.");
log.trace("START ==> LOG 5 : Inside EmployeeController getAllEmployee method.");
log.error("getEmployee error");
```

