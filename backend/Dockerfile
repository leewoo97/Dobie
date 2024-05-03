FROM openjdk:17-slim

# 필요한 패키지 설치 (Docker 클라이언트, curl)
RUN apt-get update && apt-get install -y docker.io curl

# Docker Compose V2 플러그인 설치
RUN mkdir -p /usr/local/lib/docker/cli-plugins && \
    curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-linux-x86_64 -o /usr/local/lib/docker/cli-plugins/docker-compose && \
    chmod +x /usr/local/lib/docker/cli-plugins/docker-compose

# Docker 소켓 마운트
VOLUME /var/run/docker.sock

ARG JAR_FILE=build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "./app.jar"]