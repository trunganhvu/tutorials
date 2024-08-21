FROM maven:3.8.1-openjdk-17-slim AS build
# Thiết lập thư mục làm việc trong image
WORKDIR /app

# Sao chép các tệp cần thiết để build dự án
COPY pom.xml .
COPY src ./src

# Tải các dependencies và build dự án
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

#COPY target/*.jar app.jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
