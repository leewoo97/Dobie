package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class makeDockerfilePathContentException extends RuntimeException {
    private final ErrorCode errorCode;

    public makeDockerfilePathContentException(){
        this.errorCode = ErrorCode.MAKE_DOCKER_FILE_PATH_CONTENT;
    }
}
