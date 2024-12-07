# Implement converter to mask sensitive log
```java
public class LogMaskConverter extends CompositeConverter<ILoggingEvent> {

    @Override
    public String transform(ILoggingEvent event, String in) {
        // Replace sensitive fields with asterisks
        in = in.replaceAll("(?<=firstName=')[^']+?(?=')|(?<=\"firstName\":\")[^\"]+?(?=\")", "****");
        in = in.replaceAll("(?<=lastName=')[^']+?(?=')|(?<=\"lastName\":)\\\"[^\"]+?(?=\")", "****");
        in = in.replaceAll("(?<=age=)\\d+(?=(,|\\s|}))|(?<=\"age\":)\\d+(?=(,|\\s|}))", "****");
        in = in.replaceAll("(?<=creditCardNumber=)\\d+(?=(,|\\s|}))|(?<=\"creditCardNumber\":)\\d+(?=(,|\\s|}))", "****");
        return in;
    }
}
```

# Add logback-spring.xml to your resources folder
```xml
<configuration>

    <!-- Define the custom converter for logging -->
    <conversionRule conversionWord="mask" converterClass="org.anhvt.springbootmasksensitivelog.config.LogMaskConverter"/>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{yyyy-MM-dd HH:mm:ss} - %mask(%msg) %n
            </pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE" />
    </root>
</configuration>
```
> conversionRule chỉ định thực thi mask với keyword là mask trong <pattern> - %mask