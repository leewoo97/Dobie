package com.dobie.backend.exception.exception.file;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DeleteFileFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;
    private String errorDetail;

    public DeleteFileFailedException(String errorMessage) {
        this.errorCode = ErrorCode.DELETE_FILE_FAILED;
        this.errorMessage = errorMessage;
    }

    public DeleteFileFailedException(String errorMessage, String errorDetail) {
        this.errorCode = ErrorCode.DELETE_FILE_FAILED;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }
}
