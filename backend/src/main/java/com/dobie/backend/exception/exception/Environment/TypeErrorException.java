package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class TypeErrorException extends RuntimeException {
    private final ErrorCode errorCode;

    public TypeErrorException(){
        this.errorCode = ErrorCode.TYPE_ERROR;
    }
}
