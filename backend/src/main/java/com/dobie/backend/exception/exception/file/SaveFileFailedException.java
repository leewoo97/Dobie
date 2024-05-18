package com.dobie.backend.exception.exception.file;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class SaveFileFailedException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public SaveFileFailedException(String errorMessage) {
        this.errorCode = ErrorCode.SAVE_FILE_FAILED;
        this.errorMessage = errorMessage;
    }
}
