package com.dobie.backend.domain.docker.dockercompose.service;

import com.dobie.backend.domain.project.dto.BackendGetResponseDto;
import com.dobie.backend.domain.project.dto.DatabaseGetResponseDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;
import com.dobie.backend.exception.exception.Environment.*;
import com.dobie.backend.exception.exception.build.DockerComposeCreateFailedException;
import com.dobie.backend.exception.exception.file.SaveFileFailedException;
import com.dobie.backend.util.command.CommandService;
import com.dobie.backend.util.file.FileManager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class DockerComposeServiceImpl implements DockerComposeService {

    FileManager fileManager = new FileManager();
    private final CommandService commandService;

    @Override
    public void createDockerComposeFile(ProjectGetResponseDto projectDto) {

        DatabaseGetResponseDto mysql = null;
        DatabaseGetResponseDto redis = null;
        DatabaseGetResponseDto mongodb = null;

        for (String databaseSeq : projectDto.getDatabaseMap().keySet()) {
            DatabaseGetResponseDto databaseDto = projectDto.getDatabaseMap().get(databaseSeq);
            if (databaseDto.getDatabaseType().equals("Mysql")) {
                mysql = databaseDto;
            } else if (databaseDto.getDatabaseType().equals("Redis")) {
                redis = databaseDto;
            } else if (databaseDto.getDatabaseType().equals("Mongodb")) {
                mongodb = databaseDto;
            }
        }

        StringBuilder dockercompose = new StringBuilder();
        dockercompose.append("version: \"3.8\"\n");
        dockercompose.append("services:\n");

        for (String backendSeq : projectDto.getBackendMap().keySet()) {
            BackendGetResponseDto backendDto = projectDto.getBackendMap().get(backendSeq);
            if (backendDto.getFramework().equals("SpringBoot(gradle)") || backendDto.getFramework().equals("SpringBoot(maven)")) {

                //Framework가 SpringBoot(gradle)이면 gradle, SpringBoot(Maven)이면 Maven
                dockercompose.append(createSpringDockerComposeFile(projectDto.getProjectDomain(),
                                                                   backendDto.getFramework(),
                                                                   backendSeq, backendDto.getServiceId(),
                                                                   backendDto.getPath(),
                                                                   backendDto.getExternalPort(),
                                                                   backendDto.getInternalPort(),
                                                                   mysql, mongodb, redis,
                                                                   projectDto.getFrontend().getInternalPort()));
            } else if (backendDto.getFramework().equals("Django")) {
                dockercompose.append(createDjangoComposeFile(projectDto.getProjectDomain(), backendSeq, backendDto.getServiceId(),
                                                             backendDto.getPath(), backendDto.getExternalPort(),
                                                             backendDto.getInternalPort(),
                                                             mysql, mongodb, redis, projectDto.getFrontend().getInternalPort()));
            } else if (backendDto.getFramework().equals("Fastapi")) {
                //Framework가 SpringBoot(gradle)이면 gradle, SpringBoot(Maven)이면 Maven
                dockercompose.append(createFastApiComposeFile(
                        backendSeq, backendDto.getServiceId(),
                        backendDto.getPath(),
                        backendDto.getExternalPort(),
                        backendDto.getInternalPort()
                        ));
            }
            else {
                throw new BackendFrameWorkNotFoundException();
            }
        }

        dockercompose.append(createReactDockerComposeFile(projectDto.getProjectDomain(),
                                                          projectDto.getFrontend().getFramework(),
                                                          projectDto.getFrontend().getServiceId(),
                                                          projectDto.getFrontend().getPath(),
                                                          projectDto.getFrontend().getExternalPort(),
                                                          projectDto.getFrontend().getInternalPort()));

        // database 설정 추가
        if (mysql != null) {
            dockercompose.append(
                createMysqlDockerComposeFile(mysql.getDatabaseId(), mysql.getDatabaseName(), mysql.getUsername(),
                                             mysql.getPassword(), mysql.getExternalPort(),
                                             mysql.getInternalPort(), mysql.getSchemaPath()));
        }
        if (redis != null) {
            dockercompose.append(
                createRedisDockerComposeFile(redis.getDatabaseId(), redis.getExternalPort(), redis.getInternalPort()));
        }
        if (mongodb != null) {
            dockercompose.append(
                createMongodbDockerComposeFile(mongodb.getDatabaseId(), mongodb.getDatabaseName(), mongodb.getUsername(),
                                               mongodb.getPassword(), mongodb.getExternalPort(), mongodb.getInternalPort()));
        }

        if (mysql != null || mongodb != null) {
            dockercompose.append("volumes:\n");
            if (mysql != null) {
                dockercompose.append("  mysql-data:\n");
            }
            if(mongodb != null) {
                dockercompose.append("  mongodb-data:\n");
            }
        }

        dockercompose.append("networks: \n");
        dockercompose.append("  dobie: \n");
        dockercompose.append("    external: true ");

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectDto.getProjectName();

        // 이미 경로에 Dockerfile이 있다면 삭제하는 코드
        Path existDockerFile = Paths.get(filePath, "docker-compose.yml");
        if(Files.exists(existDockerFile)) {
            commandService.deleteFile("docker-compose.yml", filePath);
        }

        try {
            fileManager.saveFile(filePath, "docker-compose.yml", dockercompose.toString());
        } catch (SaveFileFailedException e) {
            throw new DockerComposeCreateFailedException(e.getErrorMessage());
        }
    }

    @Override
    public String createSpringDockerComposeFile(String domain, String frameWork, String seq, String serviceId, String path,
                                                int externalPort, int internalPort,
                                                DatabaseGetResponseDto mysql, DatabaseGetResponseDto mongodb,
                                                DatabaseGetResponseDto redis, int frontInternalPort) {
        StringBuilder sb = new StringBuilder();

        //Framework가 SpringBoot(gradle)이면 gradle, SpringBoot(maven)이면 maven
        if (frameWork.equals("SpringBoot(gradle)")) {
            sb.append("  spring-boot-gradle").append(seq).append(":\n");
        } else if (frameWork.equals("SpringBoot(maven)")) {
            sb.append("  spring-boot-maven").append(seq).append(":\n");
        }

        sb.append("    container_name: ").append(serviceId).append("\n");
        sb.append("    build:\n");
        sb.append("      context: .").append(path).append("\n");
        sb.append("    ports:\n");
        sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
        sb.append("    volumes:\n");
        sb.append("      - /var/run/docker.sock:/var/run/docker.sock\n");

        if (mysql != null || redis != null || mongodb != null) {
            sb.append("    depends_on:\n");
            if (mysql != null) {
                sb.append("      - mysql\n");
            }
            if (redis != null) {
                sb.append("      - redis\n");
            }
            if (mongodb != null) {
                sb.append("      - mongodb\n");
            }
        }

        sb.append("    environment:\n");
        if (mysql != null || redis != null || mongodb != null) {
            if (mysql != null) {
                sb.append("      SPRING_DATASOURCE_URL: jdbc:mysql://mysql/").append(mysql.getDatabaseName())
                  .append("?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC\n");
                sb.append("      SPRING_DATASOURCE_USERNAME: ").append(mysql.getUsername()).append("\n");
                sb.append("      SPRING_DATASOURCE_PASSWORD: ").append(mysql.getPassword()).append("\n");
//                    아래는 jpa내용. 입력하지않으면 백엔드 코드기반으로 알아서 돌아감
//                    sb.append("      SPRING_JPA_HIBERNATE_DDL_AUTO: update\n");
//                    sb.append("      SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT: org.hibernate.dialect.MySQL8Dialect\n");
            }
            if (redis != null) {
                sb.append("      SPRING_DATA_REDIS_HOST: redis\n");
                sb.append("      SPRING_DATA_REDIS_PORT: 6379\n");
            }
            if (mongodb != null) {
                sb.append("      SPRING_DATA_MONGODB_URI: mongodb://").append(mongodb.getUsername()).append(":")
                  .append(mongodb.getPassword()).append("@mongodb:").append(mongodb.getExternalPort()).append("/")
                  .append(mongodb.getDatabaseName()).append("?authSource=admin&authMechanism=SCRAM-SHA-1\n");
                sb.append("      MONGO_INITDB_ROOT_USERNAME: ").append(mongodb.getUsername()).append("\n");
                sb.append("      MONGO_INITDB_ROOT_PASSWORD: ").append(mongodb.getPassword()).append("\n");
            }
        }

        sb.append("      CORS_ALLOWED_ORIGIN: http://").append(domain).append(":").append(frontInternalPort).append("\n");

        // network
        sb.append("    networks:\n");
        sb.append("      - ").append("dobie").append("\n");

        return sb.toString();

    }

    @Override
    public String createFastApiComposeFile(String seq, String serviceId, String path, int externalPort, int internalPort) {
        StringBuilder sb = new StringBuilder();

        //Framework는 Fastapi
        sb.append("  fast-api").append(seq).append(":\n");

        sb.append("    container_name: ").append(serviceId).append("\n");
        sb.append("    build:\n");
        sb.append("      context: .").append(path).append("\n");
        sb.append("    ports:\n");
        sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
        sb.append("    volumes:\n");
        sb.append("      - ./app:/rec/app\n");
        sb.append("    environment:\n");
        sb.append("      - ENV_FILE=.env.dev\n");


//        sb.append("      CORS_ALLOWED_ORIGIN: http://").append(domain).append(":").append(frontInternalPort).append("\n");

        // network
        sb.append("    networks:\n");
        sb.append("      - ").append("dobie").append("\n");

        return sb.toString();
    }

    private String createDjangoComposeFile(String domain, String seq, String serviceId, String path,
                                           int externalPort, int internalPort, DatabaseGetResponseDto mysql,
                                           DatabaseGetResponseDto mongodb, DatabaseGetResponseDto redis,
                                           int frontInternalPort) {
        StringBuilder sb = new StringBuilder();

        sb.append("  django").append(seq).append(":\n");
        sb.append("    container_name: ").append(serviceId).append("\n");
        sb.append("    build:\n");
        sb.append("      context: .").append(path).append("\n");
        sb.append("    ports:\n");
        sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
        sb.append("    volumes:\n");
        sb.append("      - /var/run/docker.sock:/var/run/docker.sock\n");
        sb.append("    environment:\n");
        sb.append("      CORS_ALLOWED_ORIGIN: http://").append(domain).append(":").append(frontInternalPort).append("\n");

        // network
        sb.append("    networks:\n");
        sb.append("      - ").append("dobie").append("\n");

        return sb.toString();
    }

    @Override
    public String createReactDockerComposeFile(String domain, String frameWork, String serviceId, String path, int externalPort,
                                               int internalPort) {
        if (frameWork.equals("React")) {
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
        } else if (frameWork.equals("Vue")) {
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
        } else {
            return "";
        }

    }

    //수정중
    @Override
    public String createMysqlDockerComposeFile(String databaseId, String databaseName, String username, String password,
                                               int externalPort,
                                               int internalPort, String schemaPath) {

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

        //DB init경로를 구성합니다. //dbInit[0]은 파일경로 dbInit[1]은 파일명
        System.out.println("스키마패스 : " + schemaPath);
        if(!schemaPath.equals("")) {
            String[] dbInit = schemaPathCut(schemaPath);
            sb.append("      - ").append(dbInit[0]).append(dbInit[1]).append(":/docker-entrypoint-initdb.d").append(dbInit[1]).append("\n");
        }
        // network
        sb.append("    networks:\n");
        sb.append("      - ").append("dobie").append("\n");

        return sb.toString();
    }

    @Override
    public String createMongodbDockerComposeFile(String databaseId, String databaseName, String username, String password,
                                                 int externalPort, int internalPort) {
        StringBuilder sb = new StringBuilder();
        sb.append("  mongodb:\n");
        sb.append("    container_name: ").append(databaseId).append("\n");
        sb.append("    image: mongo:latest\n");
        sb.append("    restart: always\n");
        sb.append("    environment:\n");
        sb.append("      MONGO_INITDB_ROOT_USERNAME: ").append(username).append("\n");
        sb.append("      MONGO_INITDB_ROOT_PASSWORD: ").append(password).append("\n");
        sb.append("      MONGO_INITDB_DATABASE: ").append(databaseName).append("\n");
        sb.append("    ports:\n");
        sb.append("      - \"").append(externalPort).append(":").append(internalPort).append("\"\n");
        sb.append("    volumes:\n");
        sb.append("      - mongodb-data:/var/lib/mongodb\n");

        // network
        sb.append("    networks:\n");
        sb.append("      - ").append("dobie").append("\n");

        return sb.toString();
    }

    @Override
    public String createRedisDockerComposeFile(String databaseId, int externalPort, int internalPort) {

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

//--------------------------------------------------------------------------------------------------------
    public String[] schemaPathCut(String schemaPath) {
        // 마지막 '/'의 위치를 찾습니다.
        int lastIndex = schemaPath.lastIndexOf('/');

        // '/' 앞의 부분을 추출합니다.
        String directory = schemaPath.substring(0, lastIndex);

        // '/' 뒤의 부분을 추출합니다.
        String filename = schemaPath.substring(lastIndex);

//         결과를 출력합니다.
//        System.out.println("Directory: " + directory);
//        System.out.println("Filename: " + filename);

        //경로와 DB초기화 파일이 실제로 존재하는지 조회
        checkDbInit(directory,filename);
        String[] returnArray = new String[2];
        returnArray[0] = directory;
        returnArray[1] = filename;
        return returnArray;
    }

    public void checkDbInit(String filepath, String dbInitFile) {
//        System.out.println("백엔드 오류 잡기 위한 파일 패스 : " + filepath);
        File directory = new File(filepath); // 디렉토리 경로 지정
        File[] filesList = directory.listFiles(); // 디렉토리의 모든 파일 및 폴더 목록 얻기
        boolean correctPath = false;
        if (filesList != null) {
            for (File file : filesList) {
                if (file.getName().equals(dbInitFile)) {
//                    System.out.println("Name: " + file.getName()); // 파일 또는 디렉토리 이름 출력
                    correctPath = true;
                    break;
                }
            }
            if (!correctPath) {
//                System.out.println("파일 경로에 bulid.gradle이 존재하지않습니다.");
                throw new DbInitNotFoundException();
            }
        } else {
//            System.out.println("파일 경로 자체가 잘못되었음.");
            throw new DbInitPathNotExistException();
        }
    }
}
