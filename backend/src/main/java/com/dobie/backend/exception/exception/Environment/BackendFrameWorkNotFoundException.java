package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class BackendFrameWorkNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public BackendFrameWorkNotFoundException(){
        this.errorCode = ErrorCode.BACKEND_FRAME_WORK_NOT_FOUND;
    }
}
