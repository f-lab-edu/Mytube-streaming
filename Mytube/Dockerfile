FROM openjdk:17-jdk-alpine

COPY . .
COPY build/libs/Mytube-0.0.1-SNAPSHOT.jar app.jar

RUN apk update && apk add --no-cache ffmpeg

ENTRYPOINT ["java", "-jar", "/app.jar"]