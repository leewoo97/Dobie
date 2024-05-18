package com.dobie.backend.domain.docker.controller;

import com.dobie.backend.domain.docker.dockerfile.service.DockerfileServiceImpl;
import com.dobie.backend.exception.format.code.ApiResponse;
import com.dobie.backend.exception.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Tag(name = "Container Check 컨트롤러", description = "Container Check Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/containercheck")
public class ProceedingContainerController {
    private final ApiResponse response;
    private final DockerfileServiceImpl dockerfileService;

    @Operation(summary = "프로젝트 별 실행중인 컨테이너 확인", description = "프로젝트 별 실행중인 컨테이너 확인")
    @GetMapping("/proceeding")
    public ResponseEntity<?> checkProceedingContainer(@RequestParam(name="projectId") String projectId) {
        HashMap<String,String> containers = dockerfileService.dockerContainerLister(projectId);

        return response.success(ResponseCode.CONTAINER_STATUS_SUCCESS,containers);
    }

    @Operation(summary = "백엔드 실행 전 DB컨테이너 실행 상태 확인", description = "백엔드 실행 전 DB컨테이너 실행 상태 확인")
    @GetMapping("/checkDB")
    public ResponseEntity<?> checkDBContainerStatus(@RequestParam(name="projectId") String projectId) {
        String result = dockerfileService.checkDBContainer(projectId);

        return response.success(ResponseCode.CHECK_DB_CONTAINER_STATUS_SUCCESS,result);
    }

    @Operation(summary = "DB 종료 전 백엔드 컨테이너 실행 상태 확인", description = "백엔드 실행 전 DB컨테이너 실행 상태 확인")
    @GetMapping("/checkBackend")
    public ResponseEntity<?> checkBackendContainerStatus(@RequestParam(name="projectId") String projectId) {
        String result = dockerfileService.checkBackendContainer(projectId);

        return response.success(ResponseCode.CHECK_BACKEND_CONTAINER_STATUS_SUCCESS,result);
    }
}
