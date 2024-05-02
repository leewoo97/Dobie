package com.dobie.backend.domain.docker.dockercompose.service;

import com.dobie.backend.domain.project.dto.BackendGetResponseDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;
import org.springframework.stereotype.Service;

@Service
public class DockerComposeServiceImpl implements DockerComposeService {

    @Override
    public void createDockerComposeFile(ProjectGetResponseDto projectGetResponseDto) {

//        boolean mysql = projectGetResponseDto.getDatabase() == null ? false : true;
//        boolean redis = false;
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("version: \"3.8\"\n");
//        sb.append("services:\n");
//
//        for (int i = 1; i <= projectGetResponseDto.getBackendMap().size(); i++) {
//            BackendGetResponseDto backendGetResponseDto = projectGetResponseDto.getBackendMap().get(i);
//            if (backendGetResponseDto.getFramework().equals("SpringBoot")) {
//                sb.append(createSpringDockerComposeFile(i, backendGetResponseDto.getPath(), backendGetResponseDto.getExternalPort(),
//                                              backendGetResponseDto.getInternalPort(), mysql, redis,
//                                              "databasename",
//                                              projectGetResponseDto.getDatabase().getUsername(),
//                                              projectGetResponseDto.getDatabase().getPassword()));
//            } else if (backendGetResponseDto.getFramework().equals("Django")) {
//
//            }
//        }
//
//        sb.append(createReactDockerComposeFile(projectGetResponseDto.getFrontend().getPath(), projectGetResponseDto.getFrontend().getExternalPort(), projectGetResponseDto.getFrontend().getInternalPort()));

        // database 설정 추가

    }

    @Override
    public String createSpringDockerComposeFile(int seq, String path, int externalPort, int internalPort, boolean mysql,
                                                boolean redis, String databaseName, String username, String password) {

        StringBuilder sb = new StringBuilder();
        sb.append("  backend").append(seq).append(":\n");
        sb.append("    build:\n");
        sb.append("      context: .").append(path).append("\n");
        sb.append("    ports:\n");
        sb.append("      - \"\n").append(externalPort).append(":").append(internalPort).append("\"\n");
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
