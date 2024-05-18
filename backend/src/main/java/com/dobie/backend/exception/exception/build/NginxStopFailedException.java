package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class NginxStopFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;
    private final String errorDetail;

    public NginxStopFailedException(String errorMessage, String errorDetail) {
        this.errorCode = ErrorCode.STOP_NGINX_FAILED;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }
}
