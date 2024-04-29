package com.dobie.backend.domain.docker.dockerfile.service;

public interface DockerfileService {

    void createGradleDockerfile(String projectName, String version, String path);

    void createMavenDockerfile(String projectName, String version, String path);

    void createReactDockerfile(String projectName,String version, String path);

    void createVueDockerfile(String projectName, String version, String path);
}
