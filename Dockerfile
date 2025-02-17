FROM maven:3.9.9-eclipse-temurin-8 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package

FROM openjdk:8-jre-slim
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]