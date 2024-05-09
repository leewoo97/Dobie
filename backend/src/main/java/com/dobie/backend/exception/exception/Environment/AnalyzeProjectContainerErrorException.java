package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class AnalyzeProjectContainerErrorException extends RuntimeException {
    private final ErrorCode errorCode;

    public AnalyzeProjectContainerErrorException(){
        this.errorCode = ErrorCode.ANALYZE_PROJECT_CONTAINER_ERROR;
    }
}
