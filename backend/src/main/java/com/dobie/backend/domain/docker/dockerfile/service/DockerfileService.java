package com.dobie.backend.domain.docker.dockerfile.service;

public interface DockerfileService {

    void createSpringDockerfile(String projectName, String version, String path);

    void createReactDockerfile(String projectName,String version, String path);
}
