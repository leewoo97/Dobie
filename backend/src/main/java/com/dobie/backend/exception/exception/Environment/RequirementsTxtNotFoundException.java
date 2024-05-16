package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class RequirementsTxtNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public RequirementsTxtNotFoundException(){
        this.errorCode = ErrorCode.REQUIREMENTS_TXT_NOT_FOUND;
    }
}
