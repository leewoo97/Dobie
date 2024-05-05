package com.dobie.backend.exception.exception.git;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GitCheckoutFailedException extends RuntimeException {
    private final ErrorCode errorCode;

    public GitCheckoutFailedException(){
        this.errorCode = ErrorCode.GIT_CHECKOUT_FAILED;
    }
}
