package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ServiceStartFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;
    private final String errorDetail;

    public ServiceStartFailedException(String errorMessage, String errorDetail) {
        this.errorCode = ErrorCode.SERVICE_START_FAILED;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }
}
