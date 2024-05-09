package com.dobie.backend.domain.docker.controller;

import com.dobie.backend.domain.docker.dockerfile.service.DockerfileServiceImpl;
import com.dobie.backend.exception.format.code.ApiResponse;
import com.dobie.backend.exception.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

}
