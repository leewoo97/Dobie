package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class CurrentStatusNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public CurrentStatusNotFoundException(){
        this.errorCode = ErrorCode.CURRENT_STATUS_NOT_FOUND;
    }
}
