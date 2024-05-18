package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class JsonToMapErrorException extends RuntimeException {
    private final ErrorCode errorCode;

    public JsonToMapErrorException(){
        this.errorCode = ErrorCode.JSON_TO_MAP_ERROR;
    }
}
