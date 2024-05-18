package com.dobie.backend.exception.exception.git;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GitPullFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public GitPullFailedException(String errorMessage, Throwable cause){
        super(cause);  // 부모 클래스인 RuntimeException에 메시지와 원인을 전달
        this.errorCode = ErrorCode.GIT_PULL_FAILED;
        this.errorMessage = errorMessage;
    }
}