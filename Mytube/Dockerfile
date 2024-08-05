FROM openjdk:17-jdk-alpine AS builder

WORKDIR /workspace/app

COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

FROM openjdk:17-jdk-alpine

RUN apk add --no-cache ffmpeg

WORKDIR /workspace/app

COPY --from=builder /workspace/app/build/libs/Mytube-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
