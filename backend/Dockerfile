FROM openjdk:17-slim

# Docker 클라이언트 설치
RUN apt-get update && apt-get install -y docker.io

# Docker 소켓 마운트
VOLUME /var/run/docker.sock

ARG JAR_FILE=build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "./app.jar"]