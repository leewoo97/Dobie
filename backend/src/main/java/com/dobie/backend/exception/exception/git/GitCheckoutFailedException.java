package com.dobie.backend.exception.exception.git;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GitCheckoutFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public GitCheckoutFailedException(String errorMessage){
        this.errorCode = ErrorCode.GIT_CHECKOUT_FAILED;
        this.errorMessage = errorMessage;
    }
}
