# === Build stage ===
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom.xml and download deps first (better layer caching)
COPY pom.xml .
RUN mvn -q -B dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn -q -B -DskipTests package

# === Runtime stage ===
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/hospital-appointment-scheduler-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","/app/app.jar"]