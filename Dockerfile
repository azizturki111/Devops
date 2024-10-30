FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B


COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp


COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app.jar"]