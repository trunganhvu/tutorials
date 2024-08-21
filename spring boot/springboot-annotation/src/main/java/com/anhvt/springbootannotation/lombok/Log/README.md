# @CommonsLog @Log @Slf4j
Cả 3 annotation đều trong thư viện lombok

## @CommonsLog
* Logger được tạo: org.apache.commons.logging.Log
* Thư viện logging: Apache Commons Logging
* Sử dụng Apache Commons Logging làm backend cho logger.
* Đây là lựa chọn tốt nếu bạn đang sử dụng Apache Commons Logging hoặc muốn một lớp trừu tượng trên các framework logging khác.

Cách sử dụng
```java
@CommonsLog
public class Cat {
    public void println() {
        log.info("start");  // 14:47:40.061 [main] INFO com.anhvt.springbootannotation.lombok.Log.Cat -- start

        log.debug("debug log"); // No print

        log.error("finish");    // 14:47:40.077 [main] ERROR com.anhvt.springbootannotation.lombok.Log.Cat -- finish
    }
}
```

## @Log
* Logger được tạo: java.util.logging.Logger
* Thư viện logging: Java Util Logging (JUL)
* Sử dụng java.util.logging.Logger, là logging framework được tích hợp sẵn trong JDK.
* Thích hợp cho các dự án muốn sử dụng thư viện logging mặc định mà không thêm phụ thuộc nào khác.
Cách sử dụng
```java
@Log
public class Dog {
    public void printlnDog() {
        log.info("info dog");   // Aug 11, 2024 2:47:40 PM com.anhvt.springbootannotation.lombok.Log.Dog printlnDog INFO: info dog

        log.fine("fine dog");   // no print
        log.severe("servere dog"); // Aug 11, 2024 2:47:40 PM com.anhvt.springbootannotation.lombok.Log.Dog printlnDog SEVERE: servere dog

        log.log(Level.WARNING, "finish");   // Aug 11, 2024 2:47:40 PM com.anhvt.springbootannotation.lombok.Log.Dog printlnDog WARNING: finish
    }
}
```

## @Slf4j
* Logger được tạo: org.slf4j.Logger
* Thư viện logging: SLF4J (Simple Logging Facade for Java)
* Sử dụng SLF4J làm backend cho logger.

Cách sử dụng
```java
@Slf4j
public class Pig {
    public void printlnPig() {
        log.info("info pig");   // 14:52:58.594 [main] INFO com.anhvt.springbootannotation.lombok.Log.Pig -- info pig

        log.error("error pig");   // 14:52:58.594 [main] ERROR com.anhvt.springbootannotation.lombok.Log.Pig -- error pig

        log.debug("debug pig"); // no print

        log.trace("trace pig");   // no print
        log.warn("warn pig");   // 14:52:58.594 [main] WARN com.anhvt.springbootannotation.lombok.Log.Pig -- warn pig

    }
}
```