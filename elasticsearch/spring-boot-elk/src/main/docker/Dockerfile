FROM openjdk:17-jdk-slim
VOLUME /tmp
ADD java-log-aggregation.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
