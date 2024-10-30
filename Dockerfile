FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/tp-foyer-5.0.0.jar app.jar
CMD ["java", "-jar", "app.jar"]