package com.dobie.backend.exception.exception.Environment;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class FrontendFrameWorkNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public FrontendFrameWorkNotFoundException(){
        this.errorCode = ErrorCode.FRONTEND_FRAME_WORK_NOT_FOUND;
    }
}
