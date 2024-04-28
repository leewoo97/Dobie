package com.dobie.backend.domain.docker.dockerfile.service;

public interface DockerfileService {

    void createBackendDockerfile(String projectName, String version, String path);

}
