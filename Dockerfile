FROM gradle:jdk23-alpine AS build
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean :cuentaflex-applications-account-service:bootJar


FROM openjdk:23-jdk-slim
COPY --from=build /app/cuentaflex/applications/account-service/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
