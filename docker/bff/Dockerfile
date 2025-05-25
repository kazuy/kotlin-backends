FROM gradle:8-jdk21-jammy AS builder

WORKDIR /app

# copy gradle files
COPY gradle.properties .
COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY gradle/libs.versions.toml gradle/

# copy project files
COPY bff bff/
COPY protos protos/
COPY stub stub/

RUN gradle :bff:shadowJar --no-daemon

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /app/bff/build/libs/bff-all.jar ./app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
