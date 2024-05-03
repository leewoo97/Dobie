package com.dobie.backend.domain.docker.dockerfile.service;

import com.dobie.backend.util.file.FileManager;
import org.springframework.stereotype.Service;

@Service
public class DockerfileServiceImpl implements DockerfileService{

    FileManager fileManager = new FileManager();

    @Override
    public void createGradleDockerfile(String projectName, String version, String path) {

        StringBuilder sb = new StringBuilder();
        sb.append("FROM openjdk:").append(version).append("-slim\n");
        sb.append("RUN apt-get update && apt-get install -y docker.io\n");
        sb.append("VOLUME /var/run/docker.sock\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY . /app\n");
        sb.append("RUN chmod +x ./gradlew\n");
        sb.append("RUN ./gradlew clean bootJar -x test\n");
        sb.append("RUN cp $(ls -t build/libs/*.jar | head -n 1) app.jar\n");
        sb.append("ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\n");

//        sb.append("RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build\n");
//        sb.append("ARG JAR_FILE=build/libs/*.jar\n");
//        sb.append("COPY ${JAR_FILE} app.jar\n");
//        sb.append("ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\n");
        String dockerfile = sb.toString();

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectName + path;
        fileManager.saveFile(filePath, "Dockerfile", dockerfile);

    }

    @Override
    public void createMavenDockerfile(String projectName, String version, String path) {

        StringBuilder sb = new StringBuilder();
        sb.append("FROM openjdk:").append(version).append("-slim\n");
        sb.append("RUN apt-get update && apt-get install -y maven\n");
        sb.append("VOLUME /var/run/docker.sock\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY . /app\n");
        sb.append("RUN mvn clean package -DskipTests\n");
        sb.append("RUN cp target/*.jar app.jar\n");
        sb.append("ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\n");
        String dockerfile = sb.toString();

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectName + path;
        fileManager.saveFile(filePath, "Dockerfile", dockerfile);

    }

    @Override
    public void createReactDockerfile(String projectName, String version, String path) {

        StringBuilder sb = new StringBuilder();
        sb.append("FROM node:").append(version).append("-alpine as build-stage\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY package*.json ./\n");
        sb.append("RUN npm install\n");
        sb.append("COPY . .\n");
        sb.append("RUN npm run build\n");
        sb.append("CMD [ \"npm\", \"start\" ]\n");
        String dockerfile = sb.toString();

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectName + path;
        fileManager.saveFile(filePath, "Dockerfile", dockerfile);

    }

    @Override
    public void createVueDockerfile(String projectName, String version, String path) {

        StringBuilder sb = new StringBuilder();
        sb.append("FROM node:").append(version).append("-alpine as build-stage\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY package*.json ./\n");
        sb.append("RUN npm install\n");
        sb.append("COPY . .\n");
        sb.append("RUN npm run build\n");
        sb.append("FROM node:20.11.0-alpine\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY --from=build-stage /app/dist /app\n");
        sb.append("CMD [\"npx\", \"http-server\", \"-p\", \"5173\"]\n");
        String dockerfile = sb.toString();

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectName + path;
        fileManager.saveFile(filePath, "Dockerfile", dockerfile);

    }

}
