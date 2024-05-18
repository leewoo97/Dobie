package com.dobie.backend.exception.exception.docker;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DockerPsErrorException extends RuntimeException {
    private final ErrorCode errorCode;

    public DockerPsErrorException(){
        this.errorCode = ErrorCode.DOCKER_PS_ERROR;
    }
}
