# --- Build stage ---
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# 1. Κατεβάζουμε μόνο dependencies για caching
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. Αντιγράφουμε τον κώδικα και κάνουμε το τελικό build
COPY src ./src
RUN mvn package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Δημιουργούμε non-root user & group
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

# Αντιγράφουμε μόνο το jar από το προηγούμενο stage
COPY --from=build /app/target/*.jar app.jar

# Δίνουμε δικαιώματα
RUN chown appuser:appgroup app.jar

# Ορίζουμε non-root user
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "--enable-preview", "-jar", "/app/app.jar"]
