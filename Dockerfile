FROM --platform=linux/amd64 eclipse-temurin:21-jdk
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
