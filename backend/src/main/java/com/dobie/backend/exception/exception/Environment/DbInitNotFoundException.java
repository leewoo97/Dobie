package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DbInitNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public DbInitNotFoundException(){
        this.errorCode = ErrorCode.DBINIT_NOT_FOUND;
    }
}
