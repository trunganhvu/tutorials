# First stage: build the Java application
FROM maven:3.9.9-amazoncorretto-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ src/
RUN mvn package

# Second stage: copy the built app from the first stage
FROM amazoncorretto:17-alpine3.18
WORKDIR /app
COPY --from=build /app/target/*.jar java-api.jar

# Default spring port or configuration port in resources
EXPOSE 8080
CMD ["java", "-jar", "java-api.jar"]