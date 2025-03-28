# Stage 1: Build with Gradle
FROM gradle:8.6-jdk21-jammy AS build
WORKDIR /home/gradle/src

# Cache dependencies
COPY build.gradle settings.gradle ./
RUN gradle dependencies --no-daemon

# Build application
COPY src ./src
RUN gradle build --no-daemon -x test

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy built artifacts
COPY --from=build /home/gradle/src/build/quarkus-app /app

# Non-root user
RUN useradd -m quarkus && \
    chown -R quarkus:quarkus /app
USER quarkus

EXPOSE 8080
EXPOSE 9000
CMD ["java", "-jar", "/app/quarkus-run.jar"]