# First stage: build the Java application using Gradle
FROM gradle:jdk17-corretto AS build

WORKDIR /app
COPY . .
RUN ./gradlew bootJar -DskipTests

# Second stage: copy the built app from the first stage
FROM amazoncorretto:17-alpine3.18
WORKDIR /app
COPY --from=build /app/build/libs/*.jar java-api.jar

# Default spring port or configuration port in resources
EXPOSE 8080
CMD ["java", "-jar", "java-api.jar"]