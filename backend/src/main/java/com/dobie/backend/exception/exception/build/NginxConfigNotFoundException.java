package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;


@Getter
public class NginxConfigNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public NginxConfigNotFoundException() {
        this.errorCode = ErrorCode.NGINX_CONF_NOT_FOUND;

    }
}
