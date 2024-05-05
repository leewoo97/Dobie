package com.dobie.backend.exception.exception.git;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GitInfoNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public GitInfoNotFoundException() {this.errorCode = ErrorCode.GIT_INFO_NOT_FOUND;}
}
