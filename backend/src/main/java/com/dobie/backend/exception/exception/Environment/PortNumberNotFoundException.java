package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class PortNumberNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public PortNumberNotFoundException(){
        this.errorCode = ErrorCode.PORT_NUMBER_NOT_FOUND;
    }
}
