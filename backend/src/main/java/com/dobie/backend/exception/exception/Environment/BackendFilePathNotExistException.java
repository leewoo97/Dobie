package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class BackendFilePathNotExistException extends RuntimeException {
    private final ErrorCode errorCode;

    public BackendFilePathNotExistException(){
        this.errorCode = ErrorCode.BACKEND_FILE_PATH_NOT_EXIST;
    }
}
