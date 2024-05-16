package com.dobie.backend.exception.handler;

import com.dobie.backend.exception.exception.Environment.*;
import com.dobie.backend.exception.exception.build.ProjectPathNotFoundException;
import com.dobie.backend.exception.exception.build.*;
import com.dobie.backend.exception.exception.docker.DockerPsErrorException;
import com.dobie.backend.exception.exception.docker.DockerPsLinePartsErrorException;
import com.dobie.backend.exception.exception.file.DeleteFileFailedException;
import com.dobie.backend.exception.exception.file.NginxFileNotFoundException;
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

    @ExceptionHandler(FastApiBuildFailedException.class)
    protected ResponseEntity<?> handle(FastApiBuildFailedException e) {
        log.error("FastApiBuildFailedException = {}", e.getErrorCode().getMessage());
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
        if (e.getErrorDetail() != null) {
            log.error("Error Detail = {}", e.getErrorDetail());
        }
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(ProjectStopFailedException.class)
    protected ResponseEntity<?> handle(ProjectStopFailedException e) {
        log.error("ProjectStopFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        if (e.getErrorDetails() != null) {
            log.error("Error Detail = {}", e.getErrorDetails());
        }
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(ServiceStopFailedException.class)
    protected ResponseEntity<?> handle(ServiceStopFailedException e) {
        log.error("ServiceStopFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        log.error("Error Detail = {}", e.getErrorDetail());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(ServiceStartFailedException.class)
    protected ResponseEntity<?> handle(ServiceStartFailedException e) {
        log.error("ServiceStopFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        log.error("Error Detail = {}", e.getErrorDetail());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(NginxRestartFailedException.class)
    protected ResponseEntity<?> handle(NginxRestartFailedException e) {
        log.error("NginxRestartFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        log.error("Error Detail = {}", e.getErrorDetail());
        return response.error(e.getErrorCode());
    }

    /* Git */
    @ExceptionHandler(GitCheckoutFailedException.class)
    protected ResponseEntity<?> handle(GitCheckoutFailedException e) {
        log.error("GitCheckoutFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        log.error("Error Detail = {}", e.getErrorDetail());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(GitCloneFailedException.class)
    protected ResponseEntity<?> handle(GitCloneFailedException e) {
        log.error("GitCloneFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        log.error("Error Detail = {}", e.getErrorDetail());
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
        log.error("Cause : {}", e.getCause().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 파일 */
    @ExceptionHandler(SaveFileFailedException.class)
    protected ResponseEntity<?> handle(SaveFileFailedException e) {
        log.error("SaveFileFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(DeleteFileFailedException.class)
    protected ResponseEntity<?> handle(DeleteFileFailedException e) {
        log.error("DeleteFileFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        log.error("Cause : {}", e.getCause().getMessage());
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

    /* json을 map으로 변환 */
    @ExceptionHandler(JsonToMapErrorException.class)
    protected ResponseEntity<?> handle(JsonToMapErrorException e) {
        log.error("JsonToMapErrorException = {}", e.getErrorCode().getMessage());
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

    @ExceptionHandler(RequirementsTxtNotFoundException.class)
    protected ResponseEntity<?> handle(RequirementsTxtNotFoundException e) {
        log.error("RequirementsTxtNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(DbInitNotFoundException.class)
    protected ResponseEntity<?> handle(DbInitNotFoundException e) {
        log.error("DbInitNotFoundException = {}", e.getErrorCode().getMessage());
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

    @ExceptionHandler(FastApiFilePathNotExistException.class)
    protected ResponseEntity<?> handle(FastApiFilePathNotExistException e) {
        log.error("FastApiFilePathNotExistException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(DbInitPathNotExistException.class)
    protected ResponseEntity<?> handle(DbInitPathNotExistException e) {
        log.error("DbInitPathNotExistException = {}", e.getErrorCode().getMessage());
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

    /* 파일 내용 조회 오류*/
    @ExceptionHandler(TypeErrorException.class)
    protected ResponseEntity<?> handle(TypeErrorException e) {
        log.error("TypeErrorException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(makeDockerfilePathContentException.class)
    protected ResponseEntity<?> handle(makeDockerfilePathContentException e) {
        log.error("makeDockerfilePathContentException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(makeDockerComposefilePathContentException.class)
    protected ResponseEntity<?> handle(makeDockerComposefilePathContentException e) {
        log.error("makeDockerComposefilePathContentException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(DockerFileContentNotFoundException.class)
    protected ResponseEntity<?> handle(DockerFileContentNotFoundException e) {
        log.error("BackendDockerFileNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(DockerComposeFileContentNotFoundException.class)
    protected ResponseEntity<?> handle(DockerComposeFileContentNotFoundException e) {
        log.error("DockerComposeFileContentNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(ContainerLogNotFoundException.class)
    protected ResponseEntity<?> handle(ContainerLogNotFoundException e) {
        log.error("ContainerLogNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(AnalyzeProjectContainerErrorException.class)
    protected ResponseEntity<?> handle(AnalyzeProjectContainerErrorException e) {
        log.error("AnalyzeProjectContainerErrorException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(DockerContainerFrameworkErrorException.class)
    protected ResponseEntity<?> handle(DockerContainerFrameworkErrorException e) {
        log.error("DockerContainerFrameworkErrorException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* NGINX */
    @ExceptionHandler(ProjectPathNotFoundException.class)
    protected ResponseEntity<?> handle(ProjectPathNotFoundException e) {
        log.error("ProjectPathNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(NginxFileNotFoundException.class)
    protected ResponseEntity<?> handle(NginxFileNotFoundException e) {
        log.error("NginxFileNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(NginxConfDeleteFailedException.class)
    protected ResponseEntity<?> handle(NginxConfDeleteFailedException e) {
        log.error("NginxRestartFailedException = {}", e.getErrorCode().getMessage());
        log.error("Error Message = {}", e.getErrorMessage());
        log.error("Error Detail = {}", e.getErrorDetail());
        return response.error(e.getErrorCode());
    }

}
