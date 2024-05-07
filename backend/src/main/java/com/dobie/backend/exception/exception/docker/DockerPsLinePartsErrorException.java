package com.dobie.backend.exception.exception.docker;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DockerPsLinePartsErrorException extends RuntimeException {
    private final ErrorCode errorCode;

    public DockerPsLinePartsErrorException(){
        this.errorCode = ErrorCode.DOCKER_PS_LINE_PARTS_ERROR;
    }
}
