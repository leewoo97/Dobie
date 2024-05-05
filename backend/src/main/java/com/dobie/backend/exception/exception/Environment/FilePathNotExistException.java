package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class FilePathNotExistException extends RuntimeException {
    private final ErrorCode errorCode;

    public FilePathNotExistException(){
        this.errorCode = ErrorCode.FILE_PATH_NOT_EXIST;
    }
}
