package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;


@Getter
public class SSLLogDeleteFailedException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMessage;
    private final String errorDetail;

    public SSLLogDeleteFailedException(String errorMessage, String errorDetail) {
        this.errorCode = ErrorCode.SSL_LOG_DELETE_FAILED;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;

    }
}
