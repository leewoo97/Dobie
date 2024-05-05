package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class BuildGradleNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public BuildGradleNotFoundException(){
        this.errorCode = ErrorCode.BUILD_GRADLE_NOT_FOUND;
    }
}
