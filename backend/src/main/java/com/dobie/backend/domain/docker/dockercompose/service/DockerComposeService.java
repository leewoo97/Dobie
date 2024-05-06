package com.dobie.backend.domain.docker.dockercompose.service;

import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;

public interface DockerComposeService {

    void createDockerComposeFile(ProjectGetResponseDto projectGetResponseDto);

    String createSpringDockerComposeFile(String frameWork, String seq, String serviceId, String path, int externalPort, int internalPort, boolean mysql,
                                         boolean redis, String databaseName, String username, String password);

    String createReactDockerComposeFile(String frameWork, String path, int externalPort, int internalPort);

    String createMysqlDockerComposeFile(String databaseName, String username, String password, int externalPort, int internalPort);

    String createRedisDockerComposeFile(int externalPort, int internalPort);
}
