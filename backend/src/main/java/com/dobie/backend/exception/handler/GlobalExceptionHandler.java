package com.dobie.backend.exception.handler;

import com.dobie.backend.exception.exception.Environment.*;
import com.dobie.backend.exception.exception.build.ProjectPathNotFoundException;
import com.dobie.backend.exception.exception.build.*;
import com.dobie.backend.exception.exception.docker.DockerPsErrorException;
import com.dobie.backend.exception.exception.docker.DockerPsLinePartsErrorException;
import com.dobie.backend.exception.exception.file.SaveFileFailedException;
import com.dobie.backend.exception.exception.git.GitCheckoutFailedException;
import com.dobie.backend.exception.exception.git.GitCloneFailedException;
import com.dobie.backend.exception.exception.git.GitInfoNotFoundException;
import com.dobie.backend.exception.exception.git.GitPullFailedException;

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

    /* 빌드 */
    @ExceptionHandler(BackendBuildFailedException.class)
    protected ResponseEntity<?> handle(BackendBuildFailedException e) {
        log.error("BackendBuildFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(DockerComposeCreateFailedException.class)
    protected ResponseEntity<?> handle(DockerComposeCreateFailedException e) {
        log.error("DockerComposeCreateFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(FrontendBuildFailedException.class)
    protected ResponseEntity<?> handle(FrontendBuildFailedException e) {
        log.error("FrontendBuildFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(NginxCreateFailedException.class)
    protected ResponseEntity<?> handle(NginxCreateFailedException e) {
        log.error("NginxCreateFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(ProjectStartFailedException.class)
    protected ResponseEntity<?> handle(ProjectStartFailedException e) {
        log.error("ProjectStartFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(ServiceStopFailedException.class)
    protected ResponseEntity<?> handle(ServiceStopFailedException e) {
        log.error("ServiceStopFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    /* Git */
    @ExceptionHandler(GitCheckoutFailedException.class)
    protected ResponseEntity<?> handle(GitCheckoutFailedException e) {
        log.error("GitCheckoutFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(GitCloneFailedException.class)
    protected ResponseEntity<?> handle(GitCloneFailedException e) {
        log.error("GitCloneFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(GitInfoNotFoundException.class)
    protected ResponseEntity<?> handle(GitInfoNotFoundException e) {
        log.error("GitInfoNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(GitPullFailedException.class)
    protected ResponseEntity<?> handle(GitPullFailedException e) {
        log.error("GitPullFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    /* 파일 */
    @ExceptionHandler(SaveFileFailedException.class)
    protected ResponseEntity<?> handle(SaveFileFailedException e) {
        log.error("SaveFileFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    /* 도커 컨테이너 분석 과정 오류 */
    @ExceptionHandler(DockerPsErrorException.class)
    protected ResponseEntity<?> handle(DockerPsErrorException e) {
        log.error("DockerPsErrorException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(DockerPsLinePartsErrorException.class)
    protected ResponseEntity<?> handle(DockerPsLinePartsErrorException e) {
        log.error("DockerPsLinePartsErrorException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 도커 컴포즈 파일 오류 */
    @ExceptionHandler(BackendFrameWorkNotFoundException.class)
    protected ResponseEntity<?> handle(BackendFrameWorkNotFoundException e) {
        log.error("BackendFrameWorkNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(FrontendFrameWorkNotFoundException.class)
    protected ResponseEntity<?> handle(FrontendFrameWorkNotFoundException e) {
        log.error("FrontendFrameWorkNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 경로 확인 */
    @ExceptionHandler(BuildGradleNotFoundException.class)
    protected ResponseEntity<?> handle(BuildGradleNotFoundException e) {
        log.error("BuildGradleNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(PomXmlNotFoundException.class)
    protected ResponseEntity<?> handle(PomXmlNotFoundException e) {
        log.error("PomXmlNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(PackageJsonNotFoundException.class)
    protected ResponseEntity<?> handle(PackageJsonNotFoundException e) {
        log.error("PackageJsonNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(BackendFilePathNotExistException.class)
    protected ResponseEntity<?> handle(BackendFilePathNotExistException e) {
        log.error("FilePathNotExistException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(FrontendFilePathNotExistException.class)
    protected ResponseEntity<?> handle(FrontendFilePathNotExistException e) {
        log.error("FrontendFilePathNotExistException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(CurrentStatusNotFoundException.class)
    protected ResponseEntity<?> handle(CurrentStatusNotFoundException e) {
        log.error("CurrentStatusNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(PortNumberNotFoundException.class)
    protected ResponseEntity<?> handle(PortNumberNotFoundException e) {
        log.error("PortNumberNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* NGINX */
    @ExceptionHandler(ProjectPathNotFoundException.class)
    protected ResponseEntity<?> handle(ProjectPathNotFoundException e) {
        log.error("ProjectPathNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

}
