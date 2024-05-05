package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class PomXmlNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public PomXmlNotFoundException(){
        this.errorCode = ErrorCode.POM_XML_NOT_FOUND;
    }
}
