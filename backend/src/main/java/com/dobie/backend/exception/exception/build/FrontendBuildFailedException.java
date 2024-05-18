package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class FrontendBuildFailedException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMessage;

    public FrontendBuildFailedException(String errorMessage){
        this.errorCode = ErrorCode.FRONTEND_BUILD_FAILED;
        this.errorMessage = errorMessage;
    }
}
