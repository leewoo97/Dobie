package com.dobie.backend.exception.format.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 빌드 */
    GIT_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "git url 정보가 없습니다."),;


    private final HttpStatus status;
    private final String message;

}
