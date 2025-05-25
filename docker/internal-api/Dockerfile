FROM gradle:8-jdk21-jammy AS builder

WORKDIR /app

# copy gradle files
COPY gradle.properties .
COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY gradle/libs.versions.toml gradle/

# copy project files
COPY internal-api internal-api/
COPY protos protos/
COPY stub stub/

RUN gradle :internal-api:shadowJar --no-daemon

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /app/internal-api/build/libs/internal-api-all.jar ./app.jar

EXPOSE 50051
CMD ["java", "-jar", "app.jar"]
