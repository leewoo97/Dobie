package com.dobie.backend.exception.format.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {;


    private final HttpStatus status;
    private final String message;

}
