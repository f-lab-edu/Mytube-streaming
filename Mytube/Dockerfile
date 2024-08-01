FROM openjdk:17-jdk AS builder

CMD ["./gradlew", "clean", "build"]
VOLUME /tmp

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# 2. 빌더로부터 jar 파일 메인 디렉토리에 복사 후 시스템 진입점 정의
FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/Mytube-0.0.1-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#
#
#CMD ["./gradlew", "clean", "build"]
#
#VOLUME /tmp
#
#ARG JAR_FILE=build/libs/*.jar
#
#COPY ${JAR_FILE} app.jar
#
#EXPOSE 8080
#
#COPY . .
#
#ENTRYPOINT ["java","-jar","/app.jar"]