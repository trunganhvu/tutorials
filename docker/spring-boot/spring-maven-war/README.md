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
# First stage: Build the WAR file using Maven
FROM maven:3.9.9-amazoncorretto-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ src/
RUN mvn clean package -DskipTests

  # Second stage: Run the application on Tomcat
FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps/
COPY --from=build /app/target/spring-war-app.war ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
```