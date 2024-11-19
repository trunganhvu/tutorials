# Spring Boot Translatable REST API


## Implement translatable REST API
- Create CustomLocaleResolver Configuration handler language in header
```java
@Configuration
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerLang = request.getHeader("Accept-Language");
        return null == headerLang || headerLang.isEmpty()
                ? Locale.getDefault()
                : Locale.forLanguageTag(headerLang);
    }
}
```
- Translator object component
```java
@Component
public class Translator {
    private static ResourceBundleMessageSource messageSource;

    @Autowired
    Translator(ResourceBundleMessageSource resourceBundleMessageSource){
        messageSource = resourceBundleMessageSource;
    }

    public static String translate(String messageCode){
        return messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
    }
}
```
- Controller 
```java
@RestController
@RequestMapping(value = "/api")
public class IndexController {

    @GetMapping("message")
    public String getMessage(){
        return translate("message.greeting");
    }
}
```

- messages.properties
```properties
message.greeting=Hi Spring Boot i18n!
```

- messages_UK.properties
```properties
message.greeting=Hi Spring Boot i18n from UK!
```

- messages_vn.properties
```properties
message.greeting=Xin chào Spring Boot i18n!
```

## CURL with language
- No setup
```properties
curl --location 'localhost:8080/api/message'

Hi Spring Boot i18n!
```
- Accept-Language: uk
```
curl --location 'localhost:8080/api/message' --header 'Accept-Language: UK'
curl --location 'localhost:8080/api/message' --header 'Accept-Language: uk'

Hi Spring Boot i18n from UK!
```

```properties
curl --location 'localhost:8080/api/message' --header 'Accept-Language: vn'

Xin chào Spring Boot i18n!
```
