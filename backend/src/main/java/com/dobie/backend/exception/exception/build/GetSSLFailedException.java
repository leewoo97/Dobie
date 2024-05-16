package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GetSSLFailedException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMessage;

    public GetSSLFailedException(String errorMessage){
        this.errorCode = ErrorCode.GET_SSL_FAILED;
        this.errorMessage = errorMessage;
    }
}
