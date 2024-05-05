package com.dobie.backend.exception.format.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* git */
    GIT_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "git url 정보가 없습니다."),
    GIT_CLONE_FAILED(HttpStatus.BAD_REQUEST, "사용자 프로젝트의 Git clone을 실패했습니다."),
    GIT_PULL_FAILED(HttpStatus.BAD_REQUEST, "사용자 프로젝트의 Git Pull을 실패했습니다."),
    GIT_CHECKOUT_FAILED(HttpStatus.BAD_REQUEST, "사용자 프로젝트의 Git Checkout에 실패했습니다."),

    /* 빌드 */
    BACKEND_BUILD_FAILED(HttpStatus.BAD_REQUEST, "백엔드 프로젝트 Dockerfile 생성에 실패했습니다."),
    FRONTEND_BUILD_FAILED(HttpStatus.BAD_REQUEST, "프론트엔드 프로젝트 Dockerfile 생성에 실패했습니다."),
    DOCKER_COMPOSE_CREATE_FAILED(HttpStatus.BAD_REQUEST, "Docker compose file 생성에 실패했습니다."),
    NGINX_CREATE_FAILED(HttpStatus.BAD_REQUEST, "Nginx config file 생성에 실패했습니다."),
    PROJECT_START_FAILED(HttpStatus.BAD_REQUEST, "프로젝트를 실행하는데 실패했습니다."),
    PROJECT_STOP_FAILED(HttpStatus.BAD_REQUEST, "프로젝트를 정지하는데 실패했습니다."),

    /* 파일 생성 */
    SAVE_FILE_FAILED(HttpStatus.BAD_REQUEST, "파일 생성에 실패했습니다."),


    /* 도커 파일 환경 변수 */
    BUILD_GRADLE_NOT_FOUND(HttpStatus.NOT_FOUND, "build.gradle이 해당 경로에 존재하지않습니다."),
    POM_XML_NOT_FOUND(HttpStatus.NOT_FOUND, "pom.xml이 해당 경로에 존재하지않습니다."),
    PACKAGE_JSON_NOT_FOUND(HttpStatus.NOT_FOUND, "package.json이 해당 경로에 존재하지않습니다."),
    FILE_PATH_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 경로입니다."),

    /* nginx 파일 생성 */
    PROJECT_PATH_NOT_FOUND(HttpStatus.NOT_FOUND, "frontend 파일 경로 찾기를 실패했습니다."),
    ;
    private final HttpStatus status;
    private final String message;

}
