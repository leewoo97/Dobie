package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ContainerLogNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public ContainerLogNotFoundException(){
        this.errorCode = ErrorCode.CONTAINER_LOG_NOT_FOUND;
    }
}
