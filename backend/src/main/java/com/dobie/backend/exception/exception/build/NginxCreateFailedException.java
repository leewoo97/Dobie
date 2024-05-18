package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class NginxCreateFailedException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMessage;

    public NginxCreateFailedException(String errorMessage){
        this.errorCode = ErrorCode.NGINX_CREATE_FAILED;
        this.errorMessage = errorMessage;
    }
}
