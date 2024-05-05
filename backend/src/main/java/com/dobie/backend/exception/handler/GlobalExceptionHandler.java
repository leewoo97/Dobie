package com.dobie.backend.exception.handler;

import com.dobie.backend.exception.exception.build.ProjectPathNotFoundException;
import com.dobie.backend.exception.format.code.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ApiResponse response;

    @ExceptionHandler(ProjectPathNotFoundException.class)
    protected ResponseEntity<?> handle(ProjectPathNotFoundException e) {
        log.error("ProjectPathNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
}
