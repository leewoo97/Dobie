package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ServiceStopFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public ServiceStopFailedException(String errorMessage) {
        this.errorCode = ErrorCode.SERVICE_STOP_FAILED;
        this.errorMessage = errorMessage;
    }
}
