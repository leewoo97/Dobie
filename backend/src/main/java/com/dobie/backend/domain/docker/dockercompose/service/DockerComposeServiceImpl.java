package com.dobie.backend.domain.docker.dockercompose.service;

import com.dobie.backend.domain.project.dto.BackendGetResponseDto;
import com.dobie.backend.domain.project.dto.DatabaseGetResponseDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;
import com.dobie.backend.util.file.FileManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DockerComposeServiceImpl implements DockerComposeService {

    FileManager fileManager = new FileManager();

    @Override
    public void createDockerComposeFile(ProjectGetResponseDto projectGetResponseDto) {

        DatabaseGetResponseDto mysql = null;
        DatabaseGetResponseDto redis = null;

        for (String databaseSeq : projectGetResponseDto.getDatabaseMap().keySet()) {
            DatabaseGetResponseDto databaseGetResponseDto = projectGetResponseDto.getDatabaseMap().get(databaseSeq);
            if (databaseGetResponseDto.getDatabaseType().equals("mysql")) {
                mysql = databaseGetResponseDto;
            } else if (databaseGetResponseDto.getDatabaseType().equals("redis")) {
                redis = databaseGetResponseDto;
            }
        }

        StringBuilder dockercompose = new StringBuilder();
        dockercompose.append("version: \"3.8\"\n");
        dockercompose.append("services:\n");

        for (String backendSeq : projectGetResponseDto.getBackendMap().keySet()) {
            BackendGetResponseDto backendGetResponseDto = projectGetResponseDto.getBackendMap().get(backendSeq);
            if (backendGetResponseDto.getFramework().equals("SpringBoot(Gradle)") || backendGetResponseDto.getFramework().equals("SpringBoot(Maven)")) {
                String databaseName = null;
                String username = null;
                String password = null;
                if (mysql != null) {
                    databaseName = mysql.getDatabaseName();
                    username = mysql.getUsername();
                    password = mysql.getPassword();
                }
                dockercompose.append(createSpringDockerComposeFile(backendSeq, backendGetResponseDto.getPath(),
                                                                   backendGetResponseDto.getExternalPort(),
                                                                   backendGetResponseDto.getInternalPort(), mysql != null,
                                                                   redis != null, databaseName, username, password));
            } else if (backendGetResponseDto.getFramework().equals("Django")) {

            }
        }

        dockercompose.append(createReactDockerComposeFile(projectGetResponseDto.getFrontend().getPath(),
                                                          projectGetResponseDto.getFrontend().getExternalPort(),
                                                          projectGetResponseDto.getFrontend().getInternalPort()));

        // database 설정 추가
        if (mysql != null) {
            dockercompose.append(
                createMysqlDockerComposeFile(mysql.getDatabaseName(), mysql.getUsername(),
                                             mysql.getPassword(), mysql.getExternalPort(),
                                             mysql.getInternalPort()));
        }
        if (redis != null) {
            dockercompose.append(
                createRedisDockerComposeFile(redis.getExternalPort(), redis.getInternalPort()));
        }

        if (mysql != null) {
            dockercompose.append("volumes:\n");
            dockercompose.append("  mysql-data:\n");
        }

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectGetResponseDto.getProjectName();
        fileManager.saveFile(filePath, "docker-compose.yml", dockercompose.toString());

    }

    @Override
    public String createSpringDockerComposeFile(String seq, String path, int externalPort, int internalPort, boolean mysql,
                                                boolean redis, String databaseName, String username, String password) {

        StringBuilder sb = new StringBuilder();
        sb.append("  backend").append(seq).append(":\n");
        sb.append("    build:\n");
        sb.append("      context: .").append(path).append("\n");
        sb.append("    ports:\n");
        sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
        sb.append("    volumes:\n");
        sb.append("      - /var/run/docker.sock:/var/run/docker.sock\n");

        if (mysql || redis) {
            sb.append("    depends_on:\n");
            if (mysql) {
                sb.append("      - mysql\n");
            }
            if (redis) {
                sb.append("      - redis\n");
            }
        }

        sb.append("    environment:\n");
        if (mysql || redis) {
            if (mysql) {
                sb.append("      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/").append(databaseName)
                  .append("?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC\n");
                sb.append("      SPRING_DATASOURCE_USERNAME: ").append(username).append("\n");
                sb.append("      SPRING_DATASOURCE_PASSWORD: ").append(password).append("\n");
                sb.append("      SPRING_JPA_HIBERNATE_DDL_AUTO: update\n");
                sb.append("      SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT: org.hibernate.dialect.MySQL8Dialect\n");
            }
            if (redis) {
                sb.append("      SPRING_DATA_REDIS_HOST: redis\n");
                sb.append("      SPRING_DATA_REDIS_PORT: 6379\n");
            }
        }
        sb.append("      CORS_ALLOWED_ORIGIN: http://localhost:3000\n");

        return sb.toString();
    }

    @Override
    public String createReactDockerComposeFile(String path, int externalPort, int internalPort) {

        StringBuilder sb = new StringBuilder();
        sb.append("  frontend:\n");
        sb.append("    build:\n");
        sb.append("      context: .").append(path).append("\n");
        sb.append("    ports:\n");
        sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");

        return sb.toString();
    }

    @Override
    public String createMysqlDockerComposeFile(String databaseName, String username, String password, int externalPort,
                                               int internalPort) {

        StringBuilder sb = new StringBuilder();
        sb.append("  mysql:\n");
        sb.append("    image: mysql:8.0\n");
        sb.append("    environment:\n");
        sb.append("      MYSQL_ROOT_PASSWORD: 1234\n");
        sb.append("      MYSQL_DATABASE: ").append(databaseName).append("\n");
        sb.append("      MYSQL_USER: ").append(username).append("\n");
        sb.append("      MYSQL_PASSWORD: ").append(password).append("\n");
        sb.append("    ports:\n");
        sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
        sb.append("    volumes:\n");
        sb.append("      - mysql-data:/var/lib/mysql\n");

        return sb.toString();
    }

    @Override
    public String createRedisDockerComposeFile(int externalPort, int internalPort) {

        StringBuilder sb = new StringBuilder();
        sb.append("  redis:\n");
        sb.append("    image: redis:latest\n");
        sb.append("    ports:\n");
        sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
        sb.append("    command: [\"redis-server\", \"--bind\", \"0.0.0.0\", \"--protected-mode\", \"no\"]\n");

        return sb.toString();
    }
}
