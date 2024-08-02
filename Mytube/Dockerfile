FROM openjdk:17-jdk-alpine AS builder

COPY . .
RUN chmod +x ./gradlew
RUN apk add --no-cache ffmpeg
COPY build/libs/Mytube-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

