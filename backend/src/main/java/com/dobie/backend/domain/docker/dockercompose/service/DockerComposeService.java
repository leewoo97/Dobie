package com.dobie.backend.domain.docker.dockercompose.service;

import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;

public interface DockerComposeService {

    void createDockerComposeFile(ProjectGetResponseDto projectGetResponseDto);

    String createSpringDockerComposeFile(String frameWork, String seq, String serviceId, String path, int externalPort, int internalPort, boolean mysql,
                                         boolean redis, String databaseName, String username, String password, int frontInternalPort);

    String createReactDockerComposeFile(String frameWork, String serviceId, String path, int externalPort, int internalPort);

    String createMysqlDockerComposeFile(String databaseId, String databaseName, String username, String password, int externalPort,
                                        int internalPort);

    String createRedisDockerComposeFile(String databaseId,int externalPort, int internalPort);
}
