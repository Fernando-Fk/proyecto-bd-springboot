# Usamos JDK 17
FROM eclipse-temurin:17-jdk

# Carpeta de trabajo
WORKDIR /app

# Copiamos el JAR generado
COPY target/edutrack-backend-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto que Render asigna dinámicamente
EXPOSE 8080

# Ejecutamos el JAR leyendo el puerto de Render
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]
