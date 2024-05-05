package com.dobie.backend.domain.docker.controller;

import com.dobie.backend.domain.docker.dockerfile.service.DockerfileServiceImpl;
import com.dobie.backend.exception.format.code.ApiResponse;
import com.dobie.backend.exception.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Path Test 컨트롤러", description = "Path Test Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pathtest")
public class PathTestController {
    private final ApiResponse response;
    private final DockerfileServiceImpl dockerfileService;

    @Operation(summary = "도커파일 설치를 위한 Gradle경로 확인", description = "도커파일 설치를 위한 Gradle경로 확인")
    @PostMapping("/gradlepath")
    public ResponseEntity<?> searchGradle(@RequestParam(name = "path") String path) {
        dockerfileService.checkBuildGradle(path);

        return response.success(ResponseCode.DOCKER_FILE_INSTALL_SUCCESS);
    }

    @Operation(summary = "도커파일 설치를 위한 pom.xml경로 확인", description = "도커파일 설치를 위한 pom.xml경로 확인")
    @PostMapping("/pompath")
    public ResponseEntity<?> searchPom(@RequestParam(name = "path") String path) {
        dockerfileService.checkBuildPom(path);

        return response.success(ResponseCode.DOCKER_FILE_INSTALL_SUCCESS);
    }


}
