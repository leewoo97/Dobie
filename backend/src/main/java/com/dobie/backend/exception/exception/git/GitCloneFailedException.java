package com.dobie.backend.exception.exception.git;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GitCloneFailedException extends RuntimeException {
    private final ErrorCode errorCode;

    public GitCloneFailedException(){
        this.errorCode = ErrorCode.GIT_CLONE_FAILED;
    }
}
