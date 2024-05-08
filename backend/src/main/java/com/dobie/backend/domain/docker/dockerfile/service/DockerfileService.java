package com.dobie.backend.domain.docker.dockerfile.service;

import java.util.HashMap;

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
    HashMap<String, String> dockerContainerLister();

    /* 도커컴포즈,파일,nginx.config 파일 조회 메소드 */
    //나중에 컨테이너안에 컨테이너 띄워놓고 실험 해보기
    /* 백엔드 도커파일 위치 조회 메소드(완성) */
    String makeDockerfilePathContent(String projectId, String serviceId, String type);

    String makeDockerComposefilePathContent(String projectId);

    /* 프론트엔드 도커파일 위치 조회 메소드 */

    /* 도커컴포즈 파일 위치 조회 메소드 */

    /* nginx.config 파일 위치 조회 메소드 */

    /* 파일 위치를 분석해서 ko2sist/dobie-be 컨테이너에 접속하여 파일을 읽어오는 메소드 */
    //도커 파일 읽어옴
    String readEnvironmentDockerFile(String filepath);
    //도커 컴포즈 파일 읽어옴
    String readEnvironmentDockerComposeFile(String filepath);
}
