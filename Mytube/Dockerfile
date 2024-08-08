FROM openjdk:17-jdk-alpine


RUN apk update && apk add --no-cache ffmpeg

COPY . .
COPY build/libs/Mytube-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]