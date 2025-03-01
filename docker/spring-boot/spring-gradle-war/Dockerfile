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
