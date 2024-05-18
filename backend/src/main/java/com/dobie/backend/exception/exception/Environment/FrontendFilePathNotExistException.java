package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class FrontendFilePathNotExistException extends RuntimeException {
    private final ErrorCode errorCode;

    public FrontendFilePathNotExistException(){
        this.errorCode = ErrorCode.FRONTEND_FILE_PATH_NOT_EXIST;
    }
}
