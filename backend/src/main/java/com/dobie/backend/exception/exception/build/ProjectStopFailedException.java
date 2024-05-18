package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ProjectStopFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;
    private String errorDetails;

    public ProjectStopFailedException(String errorMessage) {
        this.errorCode = ErrorCode.PROJECT_STOP_FAILED;
        this.errorMessage = errorMessage;
    }
    public ProjectStopFailedException(String errorMessage, String errorDetails) {
        this.errorCode = ErrorCode.PROJECT_STOP_FAILED;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }
}
