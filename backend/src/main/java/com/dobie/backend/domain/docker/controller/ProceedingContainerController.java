package com.dobie.backend.domain.docker.controller;

import com.dobie.backend.domain.docker.dockerfile.service.DockerfileServiceImpl;
import com.dobie.backend.exception.format.code.ApiResponse;
import com.dobie.backend.exception.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Container Check 컨트롤러", description = "Container Check Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/containercheck")
public class ProceedingContainerController {
    private final ApiResponse response;
    private final DockerfileServiceImpl dockerfileService;

    @Operation(summary = "실행중인 컨테이너 확인", description = "실행중인 컨테이너 확인")
    @GetMapping("/proceeding")
    public ResponseEntity<?> checkProceedingContainer() {
        dockerfileService.dockerContainerLister();

        return response.success(ResponseCode.DOCKER_FILE_INSTALL_SUCCESS);
    }

}
