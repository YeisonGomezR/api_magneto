FROM openjdk:15-jdk-alpine
COPY target/scanerDNA-1.0.0.jar app.jar
EXPOSE 8097:8097
ENTRYPOINT ["java", "-jar", "/app.jar"]