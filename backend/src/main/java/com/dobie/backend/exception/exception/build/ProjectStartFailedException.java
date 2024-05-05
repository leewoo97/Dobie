package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ProjectStartFailedException extends RuntimeException {
    private final ErrorCode errorCode;

    public ProjectStartFailedException() {
        this.errorCode = ErrorCode.PROJECT_START_FAILED;
    }
}
