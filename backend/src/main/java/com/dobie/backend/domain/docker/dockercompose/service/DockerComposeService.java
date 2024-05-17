package com.dobie.backend.domain.docker.dockercompose.service;

import com.dobie.backend.domain.project.dto.DatabaseGetResponseDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;

public interface DockerComposeService {

    void createDockerComposeFile(ProjectGetResponseDto projectGetResponseDto);

    String createSpringDockerComposeFile(String domain, String frameWork, String seq, String serviceId, String path,
                                         int externalPort, int internalPort,
                                         DatabaseGetResponseDto mysql, DatabaseGetResponseDto mongodb,
                                         DatabaseGetResponseDto redis, int frontInternalPort);

    String createFastApiComposeFile(String seq, String serviceId, String path,
                                         int externalPort, int internalPort);

    String createReactDockerComposeFile(String domain, String frameWork, String serviceId, String path, boolean usingNginx, int externalPort, int internalPort);

    String createMysqlDockerComposeFile(String databaseId, String databaseName, String username, String password, int externalPort,
                                        int internalPort, String schemaPath);

    String createMongodbDockerComposeFile(String databaseId, String databaseName, String username, String password, int externalPort, int internalPort);

    String createRedisDockerComposeFile(String databaseId,int externalPort, int internalPort);
}
