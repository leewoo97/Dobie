package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class makeDockerComposefilePathContentException extends RuntimeException {
    private final ErrorCode errorCode;

    public makeDockerComposefilePathContentException(){
        this.errorCode = ErrorCode.MAKE_DOCKER_COMPOSE_FILE_PATH_CONTENT;
    }
}
