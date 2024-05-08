package com.dobie.backend.exception.exception.file;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class NginxFileNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public NginxFileNotFoundException(){
        this.errorCode = ErrorCode.NGINX_CONFIG_NOT_FOUND;
    }
}
