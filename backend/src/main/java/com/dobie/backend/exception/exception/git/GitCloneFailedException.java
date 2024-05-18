package com.dobie.backend.exception.exception.git;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GitCloneFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;
    private String errorDetail;

    public GitCloneFailedException(String errorMessage){
        this.errorCode = ErrorCode.GIT_CLONE_FAILED;
        this.errorMessage = errorMessage;
    }
    public GitCloneFailedException(String errorMessage, String errorDetail){
        this.errorCode = ErrorCode.GIT_CLONE_FAILED;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }
}
