package com.dobie.backend.domain.docker.dockerfile.service;

import java.util.HashMap;

public interface DockerfileService {

    void createGradleDockerfile(String projectName, String version, String path);

    void createMavenDockerfile(String projectName, String version, String path);

    void createReactDockerfile(String projectName,String version, String path);

    void createVueDockerfile(String projectName, String version, String path);

    void createFastApiDockerfile(String projectName, String version, String path);

    void createDjangoDockerfile(String projectName, String version, String path, int internalPort);

    /* 경로 확인 메소드 */
    void checkBuildGradle(String filepath);

    void checkBuildPom(String filepath);

    void checkBuildPackageJson(String filepath);

    void checkRequirementsTxt(String filepath);

    /* 실행중인 컨테이너 확인 메소드 */
    HashMap<String,String> dockerContainerLister(String projectId);

    /* 실행중인 컨테이너 확인 메소드(프레임워크도 확인함) */
    String checkDBContainer(String projectId);

    String checkBackendContainer(String projectId);

    /* 도커파일 위치 조회 메소드 */
    String makeDockerfilePathContent(String projectId, String serviceId, String type);
    /* 도커컴포즈 파일 위치 조회 메소드 */
    String makeDockerComposefilePathContent(String projectId);

    /* 파일 위치를 분석해서 ko2sist/dobie-be 컨테이너에 접속하여 파일을 읽어오는 메소드 */
    //도커 파일 읽어옴
    String readEnvironmentDockerFile(String filepath);
    //도커 컴포즈 파일 읽어옴
    String readEnvironmentDockerComposeFile(String filepath);

    String readContainerLog(String mountId);
}
