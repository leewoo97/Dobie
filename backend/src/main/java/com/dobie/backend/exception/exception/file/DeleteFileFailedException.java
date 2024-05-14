package com.dobie.backend.exception.exception.file;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DeleteFileFailedException extends RuntimeException {
    private final ErrorCode errorCode;

    public DeleteFileFailedException() {
        this.errorCode = ErrorCode.DELETE_FILE_FAILED;
    }
}
