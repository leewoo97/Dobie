package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class FastApiFilePathNotExistException extends RuntimeException {
    private final ErrorCode errorCode;

    public FastApiFilePathNotExistException(){
        this.errorCode = ErrorCode.FAST_API_FILE_PATH_NOT_EXIST;
    }
}
