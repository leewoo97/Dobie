package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DockerContainerFrameworkErrorException extends RuntimeException {
    private final ErrorCode errorCode;

    public DockerContainerFrameworkErrorException(){
        this.errorCode = ErrorCode.DOCKER_CONTAINER_FRAMEWORK_ERROR;
    }
}
