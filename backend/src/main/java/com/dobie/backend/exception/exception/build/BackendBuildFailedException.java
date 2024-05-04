package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class BackendBuildFailedException extends RuntimeException{
    private final ErrorCode errorCode;

    public BackendBuildFailedException(){
        this.errorCode = ErrorCode.BACKEND_BUILD_FAILED;
    }
}
