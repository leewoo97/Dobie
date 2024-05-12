package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class NginxRestartFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;
    private final String errorDetail;

    public NginxRestartFailedException(String errorMessage, String errorDetail) {
        this.errorCode = ErrorCode.SERVICE_STOP_FAILED;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }
}
