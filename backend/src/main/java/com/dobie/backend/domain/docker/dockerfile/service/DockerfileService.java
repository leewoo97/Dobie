package com.dobie.backend.domain.docker.dockerfile.service;

import com.dobie.backend.domain.docker.dockerfile.dto.DockerContainerDto;

import java.util.List;

public interface DockerfileService {

    void createGradleDockerfile(String projectName, String version, String path);

    void createMavenDockerfile(String projectName, String version, String path);

    void createReactDockerfile(String projectName,String version, String path);

    void createVueDockerfile(String projectName, String version, String path);

    /* 경로 확인 메소드 */
    void checkBuildGradle(String filepath);

    void checkBuildPom(String filepath);

    void checkBuildPackageJson(String filepath);

    /* 실행중인 컨테이너 확인 메소드 */
    void dockerContainerLister();

    List<DockerContainerDto> parseDockerPsOutput(String output);
}
