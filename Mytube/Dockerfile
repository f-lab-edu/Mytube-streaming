FROM openjdk:17-jdk-alpine AS builder

RUN apk update && apk add --no-cache ffmpeg

COPY . /application/
COPY build/libs/Mytube-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]