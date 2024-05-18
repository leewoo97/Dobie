package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DjangoBuildFailedException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMessage;

    public DjangoBuildFailedException(String errorMessage){
        this.errorCode = ErrorCode.FAST_API_BUILD_FAILED;
        this.errorMessage = errorMessage;
    }
}
