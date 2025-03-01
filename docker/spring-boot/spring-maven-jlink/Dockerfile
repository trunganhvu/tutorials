# First stage: Build the Java application
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ src/
RUN mvn package -DskipTests

# Extract dependencies from the fat JAR
RUN jar xf target/*.jar

# Analyze dependencies to determine required Java modules
RUN jdeps --ignore-missing-deps -q  \
    --recursive  \
    --multi-release 17  \
    --print-module-deps  \
    --class-path 'BOOT-INF/lib/*'  \
    target/*.jar > deps.info

# Create a minimal JRE using jlink
RUN jlink \
    --verbose \
    --add-modules $(cat deps.info),java.base,java.logging,java.sql \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /custom_jre

# Remove unnecessary files to reduce build image size
RUN rm -rf /var/cache/apk/* /tmp/* /var/tmp/* /app/src

# Second stage: Use the minimal JRE in a smaller base image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Set the custom JRE path
ENV JAVA_HOME=/custom_jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Copy the minimal JRE
COPY --from=build /custom_jre $JAVA_HOME

# Copy the built application JAR
COPY --from=build /app/target/*.jar java-api.jar

# Verify Java installation
RUN ls -l ${JAVA_HOME}/bin && ${JAVA_HOME}/bin/java -version

# Default Spring Boot port
EXPOSE 8080

CMD ["java", "-jar", "java-api.jar"]