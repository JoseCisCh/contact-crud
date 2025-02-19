# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-8 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package

# Stage 2: Run the application
FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar


EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
