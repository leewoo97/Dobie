package com.dobie.backend.exception.exception.git;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GitCloneFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public GitCloneFailedException(String errorMessage){
        this.errorCode = ErrorCode.GIT_CLONE_FAILED;
        this.errorMessage = errorMessage;
    }
}
