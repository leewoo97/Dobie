package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class FastApiBuildFailedException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMessage;

    public FastApiBuildFailedException(String errorMessage){
        this.errorCode = ErrorCode.FAST_API_BUILD_FAILED;
        this.errorMessage = errorMessage;
    }
}
