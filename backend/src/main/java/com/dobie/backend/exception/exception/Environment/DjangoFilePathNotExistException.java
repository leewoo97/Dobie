package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DjangoFilePathNotExistException extends RuntimeException {
    private final ErrorCode errorCode;

    public DjangoFilePathNotExistException(){
        this.errorCode = ErrorCode.FAST_API_FILE_PATH_NOT_EXIST;
    }
}
