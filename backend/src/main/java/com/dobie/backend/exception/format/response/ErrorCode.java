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
    FAST_API_BUILD_FAILED(HttpStatus.BAD_REQUEST, "FASTAPI 프로젝트 Dockerfile 생성에 실패했습니다."),
    DOCKER_COMPOSE_CREATE_FAILED(HttpStatus.BAD_REQUEST, "Docker compose file 생성에 실패했습니다."),
    NGINX_CREATE_FAILED(HttpStatus.BAD_REQUEST, "Nginx config file 생성에 실패했습니다."),
    PROJECT_START_FAILED(HttpStatus.BAD_REQUEST, "프로젝트를 실행하는데 실패했습니다."),
    PROJECT_STOP_FAILED(HttpStatus.BAD_REQUEST, "프로젝트를 정지하는데 실패했습니다."),
    SERVICE_STOP_FAILED(HttpStatus.BAD_REQUEST, "개별 서비스를 정지하는데 실패했습니다."),
    SERVICE_START_FAILED(HttpStatus.BAD_REQUEST, "개별 서비스를 실행하는데 실패했습니다."),

    /* 파일 생성 */
    SAVE_FILE_FAILED(HttpStatus.BAD_REQUEST, "파일 생성에 실패했습니다."),
    DELETE_FILE_FAILED(HttpStatus.BAD_REQUEST, "파일 삭제에 실패했습니다."),

    /* json을 map으로 변환 */
    JSON_TO_MAP_ERROR(HttpStatus.BAD_REQUEST, "json파일을 map으로 변환하는 과정에 오류가 발생했습니다."),


    /* 도커 컴포즈 파일 환경 변수 */
    BACKEND_FRAME_WORK_NOT_FOUND(HttpStatus.NOT_FOUND, "백엔드 Framework가 제대로 지정되지 않았습니다."),
    FRONTEND_FRAME_WORK_NOT_FOUND(HttpStatus.NOT_FOUND, "프론트엔드 Framework가 제대로 지정되지 않았습니다."),

    /* 도커 파일 환경 변수 */
    BUILD_GRADLE_NOT_FOUND(HttpStatus.NOT_FOUND, "build.gradle이 해당 경로에 존재하지않습니다."),
    POM_XML_NOT_FOUND(HttpStatus.NOT_FOUND, "pom.xml이 해당 경로에 존재하지않습니다."),
    PACKAGE_JSON_NOT_FOUND(HttpStatus.NOT_FOUND, "package.json이 해당 경로에 존재하지않습니다."),
    REQUIREMENTS_TXT_NOT_FOUND(HttpStatus.NOT_FOUND, "requirements.txt가 해당 경로에 존재하지않습니다."),
    DBINIT_NOT_FOUND(HttpStatus.NOT_FOUND, "DB초기화 파일이 해당 경로에 존재하지않습니다."),
    BACKEND_FILE_PATH_NOT_EXIST(HttpStatus.NOT_FOUND, "명시하신 백엔드 경로는 존재하지 않는 경로입니다."),
    FRONTEND_FILE_PATH_NOT_EXIST(HttpStatus.NOT_FOUND, "명시하신 프론트엔드 경로는 존재하지 않는 경로입니다."),
    FAST_API_FILE_PATH_NOT_EXIST(HttpStatus.NOT_FOUND, "명시하신 FastAPI 경로는 존재하지 않는 경로입니다."),
    DBINIT_FILE_PATH_NOT_EXIST(HttpStatus.NOT_FOUND, "명시하신 DB초기화 파일 경로는 존재하지 않는 경로입니다."),
    CURRENT_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, "현재 상태를 조회할 수 없습니다."),
    PORT_NUMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "외부 또는 내부 포트번호가 존재하지않습니다."),

    /*파일 내용 조회 */
    TYPE_ERROR(HttpStatus.NOT_FOUND, "도커 파일 경로 조회메소드에 잘못된 타입이 입력되었습니다."),
    MAKE_DOCKER_FILE_PATH_CONTENT(HttpStatus.NOT_FOUND, "도커 파일 경로 조회과정에서 오류가 발생했습니다."),
    MAKE_DOCKER_COMPOSE_FILE_PATH_CONTENT(HttpStatus.NOT_FOUND, "도커컴포즈 파일 경로 조회과정에서 오류가 발생했습니다."),
    DOCKER_FILE_CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "도커 파일 경로 조회는 성공했지만, 도커 파일 내용 조회 과정에서 오류가 발생했습니다."),
    DOCKER_COMPOSE_FILE_CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "도커컴포즈 파일 경로 조회는 성공했지만, 도커컴포즈 파일 내용 조회 과정에서 오류가 발생했습니다."),
    CONTAINER_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "컨테이너 로그를 가져올수 없습니다."),
    ANALYZE_PROJECT_CONTAINER_ERROR(HttpStatus.NOT_FOUND, "프로젝트 내부 아이디 분석중 오류가 발생했습니다."),
    DOCKER_CONTAINER_FRAMEWORK_ERROR(HttpStatus.NOT_FOUND, "프로젝트 내부 프레임워크 분석중 오류가 발생했습니다. / checkDBContainerStatus API 오류"),


    /* 도커 명령어 실행 중 오류 */
    DOCKER_PS_ERROR(HttpStatus.NOT_FOUND, "docker ps 명령어 실행 중 에러 발생"),
    DOCKER_PS_LINE_PARTS_ERROR(HttpStatus.NOT_FOUND, "docker ps 명령어 실행 이후 문자열 분해과정에서 예외가 발생했습니다"),

    /* nginx 파일 */
    PROJECT_PATH_NOT_FOUND(HttpStatus.NOT_FOUND, "frontend 파일 경로 찾기를 실패했습니다."),
    NGINX_CONFIG_NOT_FOUND(HttpStatus.NOT_FOUND, "nginx config 파일 경로 찾기를 실패했습니다."),
    NGINX_CONFIG_READ_FAILED(HttpStatus.BAD_REQUEST, "nginx 파일을 읽어올 수 없습니다."),
    NGINX_RESTART_FAILED(HttpStatus.BAD_REQUEST, "nginx container를 재실행하는데 실패했습니다."),
    NGINX_CONF_DELETE_FAILED(HttpStatus.BAD_REQUEST, "nginx config 파일 삭제를 실패했습니다.")
    ;
    private final HttpStatus status;
    private final String message;

}
