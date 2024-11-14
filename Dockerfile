# Dockerfile
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY applications/app-service/build/libs/app-service.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
