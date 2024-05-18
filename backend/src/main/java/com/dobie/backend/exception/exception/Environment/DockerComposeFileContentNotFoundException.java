package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DockerComposeFileContentNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public DockerComposeFileContentNotFoundException(){
        this.errorCode = ErrorCode.DOCKER_COMPOSE_FILE_CONTENT_NOT_FOUND;
    }
}
