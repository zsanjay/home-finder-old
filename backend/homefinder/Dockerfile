FROM eclipse-temurin:21.0.3_9-jdk as builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

# ---- Runtime Stage ----
FROM eclipse-temurin:21.0.3_9-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]





