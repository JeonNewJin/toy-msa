FROM eclipse-temurin:25-jdk AS builder
WORKDIR /workspace
COPY . .
RUN ./gradlew :apps:apigateway-service:bootJar -x test --no-daemon

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /workspace/apps/apigateway-service/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
