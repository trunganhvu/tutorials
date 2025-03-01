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
