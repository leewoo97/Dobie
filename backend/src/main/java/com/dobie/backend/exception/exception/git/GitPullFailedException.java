package com.dobie.backend.exception.exception.git;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GitPullFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public GitPullFailedException(String errorMessage){
        this.errorCode = ErrorCode.GIT_PULL_FAILED;
        this.errorMessage = errorMessage;
    }
}
