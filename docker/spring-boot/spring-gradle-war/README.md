# Dev env
## Execute
```sh
docker compose up -d
# OR
docker build -t my-api .
docker run -p 8080:8080 my-api
```

### docker-compose.yml
```yaml
version: '3.9'

services:
  spring-api:
    container_name: spring-api
    image: spring-api:dev
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - '8080:8080'
    environment:
      - SPRING_PROFILES_ACTIVE=dev
    restart: unless-stopped
```

### Dockerfile
```yml
# First stage: Build the WAR file using Gradle
FROM gradle:8.6-jdk17-alpine AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle clean build -x test

  # Second stage: Run the application on Tomcat
FROM tomcat:10.1-jdk17

LABEL maintainer=trunganhvu

WORKDIR /usr/local/tomcat/webapps/
COPY --from=build /app/build/libs/*.war ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
```

## Configuration application
### SpringGradleWarApplication.java
```java
@SpringBootApplication
public class SpringGradleWarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGradleWarApplication.class, args);
    }

    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean<ErrorPageFilter> disableErrorPageFilter(ErrorPageFilter filter) {
        FilterRegistrationBean<ErrorPageFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
}
```

### ServletInitializer.java
```java
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringGradleWarApplication.class);
    }
}
```
