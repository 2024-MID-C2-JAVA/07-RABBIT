FROM gradle:7.6.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN ls
# Construir la aplicación
RUN ls
# Otorga permisos de ejecución al archivo gradlew
RUN chmod +x ./gradlew
# RUN ./gradlew app-service:bootJar

RUN ./gradlew app-service-rabbit:bootJar

# Etapa de ejecución

FROM openjdk:17-jdk-slim
WORKDIR /app
RUN ["ls"]
# Copiar el archivo JAR generado desde la etapa de construcción
# COPY --from=build /app/applications/app-service/build/libs/*.jar app.jar

COPY --from=build /app/applications/app-service-rabbit/build/libs/*.jar appLog.jar

# Configurar las opciones de Java
ENV JAVA_TOOL_OPTIONS="-XX:InitialHeapSize=1024m -XX:MaxHeapSize=2048m -XX:MaxRAMPercentage=80 -Xmx2048m -Xms1024m"

# Comando para ejecutar la aplicación
# ENTRYPOINT ["java", "-jar", "/app/app.jar"]
#
ENTRYPOINT ["java", "-jar", "/app/appLog.jar"]


# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8089
