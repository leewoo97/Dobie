package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DockerFileContentNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public DockerFileContentNotFoundException(){
        this.errorCode = ErrorCode.DOCKER_FILE_CONTENT_NOT_FOUND;
    }
}
