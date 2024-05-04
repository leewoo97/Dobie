package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class NginxCreateFailedException extends RuntimeException{
    private final ErrorCode errorCode;

    public NginxCreateFailedException(){
        this.errorCode = ErrorCode.NGINX_CREATE_FAILED;
    }
}
