package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class BackendBuildFailedException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMessage;

    public BackendBuildFailedException(String errorMessage){
        this.errorCode = ErrorCode.BACKEND_BUILD_FAILED;
        this.errorMessage = errorMessage;
    }
}
