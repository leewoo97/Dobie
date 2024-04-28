package com.dobie.backend.domain.docker.dockerfile.service;

import com.dobie.backend.util.file.FileManager;
import org.springframework.stereotype.Service;

@Service
public class DockerfileServiceImpl implements DockerfileService{

    FileManager fileManager = new FileManager();

    @Override
    public void createBackendDockerfile(String projectName, String version, String path) {

        StringBuilder sb = new StringBuilder();
        sb.append("FROM openjdk:").append(version).append("-slim\n");
        sb.append("RUN apt-get update && apt-get install -y docker.io\n");
        sb.append("VOLUME /var/run/docker.sock\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY . /app\n");
        sb.append("RUN chmod +x ./gradlew\n");
        sb.append("RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build\n");
        sb.append("ARG JAR_FILE=build/libs/*.jar\n");
        sb.append("COPY ${JAR_FILE} app.jar\n");
        sb.append("ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\n");
        String dockerfile = sb.toString();

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "~/home/" + projectName + path;
        fileManager.saveFile(filePath, "Dockerfile", dockerfile);

    }

}
