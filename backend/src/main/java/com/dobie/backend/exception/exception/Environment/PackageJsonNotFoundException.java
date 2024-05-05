package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class PackageJsonNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public PackageJsonNotFoundException(){
        this.errorCode = ErrorCode.PACKAGE_JSON_NOT_FOUND;
    }
}
