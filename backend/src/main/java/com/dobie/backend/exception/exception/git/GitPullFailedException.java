package com.dobie.backend.exception.exception.git;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GitPullFailedException extends RuntimeException {
    private final ErrorCode errorCode;

    public GitPullFailedException(){
        this.errorCode = ErrorCode.GIT_PULL_FAILED;
    }
}
