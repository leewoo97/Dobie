package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DbInitPathNotExistException extends RuntimeException {
    private final ErrorCode errorCode;

    public DbInitPathNotExistException(){
        this.errorCode = ErrorCode.DBINIT_FILE_PATH_NOT_EXIST;
    }
}
