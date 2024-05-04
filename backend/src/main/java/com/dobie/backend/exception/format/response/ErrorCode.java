package com.dobie.backend.exception.format.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 빌드 */
    GIT_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "git url 정보가 없습니다."),
    GIT_CLONE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "사용자 프로젝트의 Git clone을 실패했습니다."),
    BACKEND_BUILD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "백엔드 프로젝트 Dockerfile 생성에 실패했습니다."),
    FRONTEND_BUILD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "프론트엔드 프로젝트 Dockerfile 생성에 실패했습니다."),
    DOCKER_COMPOSE_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Docker compose file 생성에 실패했습니다."),
    NGINX_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Nginx config file 생성에 실패했습니다."),
    ;


    private final HttpStatus status;
    private final String message;

}
