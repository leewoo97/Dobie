package com.dobie.backend.domain.docker.dockercompose.service;

import com.dobie.backend.domain.project.dto.BackendGetResponseDto;
import com.dobie.backend.domain.project.dto.DatabaseGetResponseDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;
import com.dobie.backend.exception.exception.Environment.BackendFrameWorkNotFoundException;
import com.dobie.backend.exception.exception.Environment.FrontendFrameWorkNotFoundException;
import com.dobie.backend.exception.exception.build.DockerComposeCreateFailedException;
import com.dobie.backend.exception.exception.file.SaveFileFailedException;
import com.dobie.backend.util.file.FileManager;

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
            if (databaseGetResponseDto.getDatabaseType().equals("Mysql")) {
                mysql = databaseGetResponseDto;
            } else if (databaseGetResponseDto.getDatabaseType().equals("Redis")) {
                redis = databaseGetResponseDto;
            }
        }

        StringBuilder dockercompose = new StringBuilder();
        dockercompose.append("version: \"3.8\"\n");
        dockercompose.append("services:\n");

        for (String backendSeq : projectGetResponseDto.getBackendMap().keySet()) {
            BackendGetResponseDto backendGetResponseDto = projectGetResponseDto.getBackendMap().get(backendSeq);
            if (backendGetResponseDto.getFramework().equals("SpringBoot(gradle)") || backendGetResponseDto.getFramework().equals("SpringBoot(maven)")) {
                String databaseName = null;
                String username = null;
                String password = null;
                if (mysql != null) {
                    databaseName = mysql.getDatabaseName();
                    username = mysql.getUsername();
                    password = mysql.getPassword();
                }
                //Framework가 SpringBoot(gradle)이면 gradle, SpringBoot(Maven)이면 Maven
                dockercompose.append(createSpringDockerComposeFile(projectGetResponseDto.getProjectDomain() ,
                        backendGetResponseDto.getFramework(),
                        backendSeq, backendGetResponseDto.getServiceId(),
                        backendGetResponseDto.getPath(),
                        backendGetResponseDto.getExternalPort(),
                        backendGetResponseDto.getInternalPort(),
                        mysql != null,
                        redis != null, databaseName, username, password,
                        projectGetResponseDto.getFrontend().getInternalPort()));
            } else if (backendGetResponseDto.getFramework().equals("Django")) {

            }
        }

        dockercompose.append(createReactDockerComposeFile(projectGetResponseDto.getProjectDomain(),
                projectGetResponseDto.getFrontend().getFramework(),
                projectGetResponseDto.getFrontend().getServiceId(),
                projectGetResponseDto.getFrontend().getPath(),
                projectGetResponseDto.getFrontend().getExternalPort(),
                projectGetResponseDto.getFrontend().getInternalPort()));

        // database 설정 추가
        if (mysql != null) {
            dockercompose.append(
                    createMysqlDockerComposeFile(mysql.getDatabaseId(), mysql.getDatabaseName(), mysql.getUsername(),
                            mysql.getPassword(), mysql.getExternalPort(),
                            mysql.getInternalPort()));
        }
        if (redis != null) {
            dockercompose.append(
                    createRedisDockerComposeFile(redis.getDatabaseId(), redis.getExternalPort(), redis.getInternalPort()));
        }

        if (mysql != null) {
            dockercompose.append("volumes:\n");
            dockercompose.append("  mysql-data:\n");
        }

        dockercompose.append("networks: \n");
        dockercompose.append("  dobie: \n");
        dockercompose.append("    external: true ");

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectGetResponseDto.getProjectName();
        try {
            fileManager.saveFile(filePath, "docker-compose.yml", dockercompose.toString());
        } catch (SaveFileFailedException e) {
            throw new DockerComposeCreateFailedException(e.getErrorMessage());
        }
    }

    @Override
    public String createSpringDockerComposeFile(String domain, String frameWork, String seq, String serviceId, String path, int externalPort, int internalPort, boolean mysql,
                                                boolean redis, String databaseName, String username, String password, int frontInternalPort) {
        StringBuilder sb = new StringBuilder();
        //Framework가 SpringBoot(gradle)이면 gradle, SpringBoot(maven)이면 maven
        if(frameWork.equals("SpringBoot(gradle)")) {
            sb.append("  spring-boot-gradle").append(seq).append(":\n");
            sb.append("    container_name: ").append(serviceId).append("\n");
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
                    sb.append("      SPRING_DATASOURCE_URL: jdbc:mysql://mysql/").append(databaseName)
                            .append("?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC\n");
                    sb.append("      SPRING_DATASOURCE_USERNAME: ").append(username).append("\n");
                    sb.append("      SPRING_DATASOURCE_PASSWORD: ").append(password).append("\n");
//                    아래는 jpa내용. 입력하지않으면 백엔드 코드기반으로 알아서 돌아감
//                    sb.append("      SPRING_JPA_HIBERNATE_DDL_AUTO: update\n");
//                    sb.append("      SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT: org.hibernate.dialect.MySQL8Dialect\n");
                }
                if (redis) {
                    sb.append("      SPRING_DATA_REDIS_HOST: redis\n");
                    sb.append("      SPRING_DATA_REDIS_PORT: 6379\n");
                }
            }
            //React인지 vue인지 찾아서 :뒤에 포트번호 바꿀것 , 그냥 사용자가 지정한 프론트 포트번호로 바꿀것
//            sb.append("      CORS_ALLOWED_ORIGIN: http://localhost:").append(frontInternalPort).append("\n");
            sb.append("      CORS_ALLOWED_ORIGIN: http://").append(domain).append(":").append(frontInternalPort).append("\n");

            // network
            sb.append("    networks:\n");
            sb.append("      - ").append("dobie").append("\n");

            return sb.toString();
        }
        else if(frameWork.equals("SpringBoot(maven)")){
            sb.append("  spring-boot-maven").append(seq).append(":\n");
            sb.append("    container_name: ").append(serviceId).append("\n");
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
//                    아래는 jpa내용. 입력하지않으면 백엔드 코드기반으로 알아서 돌아감
//                    sb.append("      SPRING_JPA_HIBERNATE_DDL_AUTO: update\n");
//                    sb.append("      SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT: org.hibernate.dialect.MySQL8Dialect\n");
                }
                if (redis) {
                    sb.append("      SPRING_DATA_REDIS_HOST: redis\n");
                    sb.append("      SPRING_DATA_REDIS_PORT: 6379\n");
                }
            }
            //React인지 vue인지 찾아서 :뒤에 포트번호 바꿀것 , 그냥 사용자가 지정한 프론트 포트번호로 바꿀것
//            sb.append("      CORS_ALLOWED_ORIGIN: http://localhost:").append(frontInternalPort).append("\n");
            sb.append("      CORS_ALLOWED_ORIGIN: http://").append(domain).append(":").append(frontInternalPort).append("\n");

            // network
            sb.append("    networks:\n");
            sb.append("      - ").append("dobie").append("\n");

            return sb.toString();

        }else{
            throw new BackendFrameWorkNotFoundException();
        }
    }

    @Override
    public String createReactDockerComposeFile(String domain, String frameWork, String serviceId, String path, int externalPort, int internalPort) {
        if(frameWork.equals("React")) {
            StringBuilder sb = new StringBuilder();
            sb.append("  react:\n");
            sb.append("    container_name: ").append(serviceId).append("\n");
            sb.append("    build:\n");
            sb.append("      context: .").append(path).append("\n");
            sb.append("    ports:\n");
            sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
//            sb.append("    environment:\n");
//            sb.append("      - BACKEND_HOST=http://").append(domain).append(":").append(8080).append("\n");

            // network
            sb.append("    networks:\n");
            sb.append("      - ").append("dobie").append("\n");

            return sb.toString();
        }else if(frameWork.equals("Vue")){
            StringBuilder sb = new StringBuilder();
            sb.append("  vue:\n");
            sb.append("    container_name: ").append(serviceId).append("\n");
            sb.append("    build:\n");
            sb.append("      context: .").append(path).append("\n");
            sb.append("    ports:\n");
            sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
//            sb.append("    environment:\n");
//            sb.append("      - BACKEND_HOST=http://").append(domain).append(":").append(8080).append("\n");

            // network
            sb.append("    networks:\n");
            sb.append("      - ").append("dobie").append("\n");

            return sb.toString();
        }else{
            throw new FrontendFrameWorkNotFoundException();
        }
    }

    @Override
    public String createMysqlDockerComposeFile(String databaseId, String databaseName, String username, String password, int externalPort,
                                               int internalPort) {

        StringBuilder sb = new StringBuilder();
        sb.append("  mysql:\n");
        sb.append("    container_name: ").append(databaseId).append("\n");
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

        // network
        sb.append("    networks:\n");
        sb.append("      - ").append("dobie").append("\n");

        return sb.toString();
    }

    @Override
    public String createRedisDockerComposeFile(String databaseId,int externalPort, int internalPort) {

        StringBuilder sb = new StringBuilder();
        sb.append("  redis:\n");
        sb.append("    container_name: ").append(databaseId).append("\n");
        sb.append("    image: redis:latest\n");
        sb.append("    ports:\n");
        sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
        sb.append("    command: [\"redis-server\", \"--bind\", \"0.0.0.0\", \"--protected-mode\", \"no\"]\n");

        // network
        sb.append("    networks:\n");
        sb.append("      - ").append("dobie").append("\n");

        return sb.toString();
    }
}
