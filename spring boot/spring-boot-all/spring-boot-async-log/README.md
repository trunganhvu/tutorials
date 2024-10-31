Để sử dụng async logging trong Spring, bạn có thể áp dụng các bước sau:
1. Thêm các phụ thuộc:Đầu tiên, bạn cần thêm các phụ thuộc cần thiết vào file `pom.xml` của dự án Spring. Dưới đây là ví dụ về việc thêm phụ thuộc Logback và Logback AsyncAppender cho việc ghi nhật ký không đồng bộ:
```xml
<dependencies>
  <!-- Logback dependencies -->
  <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
  </dependency>
  <dependency>
      <groupId>ch.qos.logback.contrib</groupId>
      <artifactId>logback-jackson</artifactId>
  </dependency>
  <dependency>
      <groupId>com.lmax</groupId>
      <artifactId>disruptor</artifactId>
      <version>3.4.2</version>
  </dependency>
</dependencies>
```

2. Cấu hình Logback:Tiếp theo, bạn cần cấu hình Logback trong file `logback.xml`. Dưới đây là một ví dụ cấu hình cho Logback AsyncAppender:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration status="WARN">

    <property name="logging.error.file.name" value="error" />
    <property name="logging.error.file.path" value="./logs"/>
    <property name="logging.file.name" value="info" />
    <property name="logging.file.path" value="./logs"/>

    <!-- Normal log appender -->
    <appender name="INFO_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--   Set info log file name     -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${logging.file.path}/${logging.file.name}_%d{yyyyMMdd}.log</fileNamePattern>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
        <!--    Filter info, debug, trace log       -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>INFO, DEBUG, TRACE</level>
        </filter>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>DENY</onMatch>
            <onMisMatch>NEUTRAL</onMisMatch>
        </filter>
    </appender>

    <!-- Error log appender -->
    <appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--   Set error log file name     -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${logging.error.file.path}/${logging.error.file.name}_%d{yyyyMMdd}.log</fileNamePattern>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>

        <!--    Filter error log       -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>WARN, INFO, DEBUG, TRACE</level>
            <onMatch>DENY</onMatch>
            <onMisMatch>NEUTRAL</onMisMatch>
        </filter>
    </appender>

    <!-- Console Appender -->
    <!-- Logging console IDE   -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- Register logging    -->
    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="ERROR_FILE"/>
        <appender-ref ref="INFO_FILE"/>
    </root>
    <!--    <logger name="com.alicp.jetcache" level="INFO" additivity="false">-->
    <!--        <appender-ref ref="CONSOLE" />-->
    <!--        <appender-ref ref="ERROR_FILE" />-->
    <!--        <appender-ref ref="INFO_FILE" />-->
    <!--    </logger>-->

</configuration>
```

3. Kích hoạt async logging trong Spring:Để kích hoạt async logging trong Spring, bạn có thể sử dụng annotation `@EnableAsync` và `@Async` trên các phương thức ghi nhật ký. Dưới đây là một ví dụ:

```java
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Logger {

    @Async
    public void logMessage(String message) {
        // Ghi nhật ký không đồng bộ ở đây
        // ...
    }
}
```

Bằng cách sử dụng `@Async` trên phương thức `logMessage()`, Spring sẽ xử lý ghi nhật ký trong một luồng khác, không làm chặn luồng chính.

Lưu ý rằng để sử dụng `@Async`, bạn cần cấu hình một `TaskExecutor` trong Spring. Bạn có thể thực hiện cấu hình này trong file cấu hình Spring (`applicationContext.xml` hoặc `application.properties`) bằng cách sử dụng `@EnableAsync` và `@Configuration`:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("AsyncLogger-");
        executor.initialize();
        return executor;
    }
}

```

Trong ví dụ trên, chúng ta đã cấu hình một `ThreadPoolTaskExecutor` với 10 luồng cố định, tối đa 100 luồng và dung lượng hàng đợi là 10.

Sau khi thực hiện các bước trên, bạn có thể sử dụng `Logger` và phương thức `logMessage()` để ghi nhật ký không đồng bộ trong ứng dụng Spring của mình.