package com.dobie.backend.domain.project.controller;

import com.dobie.backend.domain.project.dto.ProjectRequestDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;
import com.dobie.backend.domain.project.service.ProjectService;
import com.dobie.backend.exception.exception.build.ProjectStartFailedException;
import com.dobie.backend.exception.format.code.ApiResponse;
import com.dobie.backend.exception.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Project 컨트롤러", description = "Project Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {

    private final ApiResponse response;
    private final ProjectService projectService;

    @Operation(summary = "전체 프로젝트", description = "전체 프로젝트")
    @GetMapping("")
    public ResponseEntity<?> getAllProjects() {
        Map<String, ProjectGetResponseDto> map = projectService.getAllProjects();
        if (map.isEmpty()) {
            return response.success(ResponseCode.PROJECT_LIST_NOT_FOUND);
        }
        return response.success(ResponseCode.PROJECT_LIST_FETCHED, map);
    }

    @Operation(summary = "프로젝트 등록", description = "프로젝트 등록")
    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequestDto dto) {
        projectService.createProject(dto);

        return response.success(ResponseCode.PROJECT_CREATE_SUCCESS, dto);
    }

    @Operation(summary = "프로젝트 수정", description = "프로젝트 수정")
    @PutMapping("/update/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable String projectId, @RequestBody ProjectRequestDto dto) {
        projectService.updateProject(projectId, dto);

        return response.success(ResponseCode.PROJECT_INFO_UPDATED, dto);
    }

    @Operation(summary = "프로젝트 빌드", description = "등록된 프로젝트(백, 프론트, db) 정보를 바탕으로 build파일 생성")
    @PostMapping("/build/{projectId}")
    public ResponseEntity<?> buildTotalProject(@PathVariable String projectId) {
        projectService.buildTotalService(projectId);

        return response.success(ResponseCode.PROJECT_BUILD_SUCCESS);
    }

    @Operation(summary = "프로젝트 등록, 빌드", description = "프로젝트 정보를 등록한 후 정보 기반으로 빌드파일 생성")
    @PostMapping("/regist/{projectId}")
    public ResponseEntity<?> registerProject(@PathVariable String projectId, @RequestBody ProjectRequestDto dto) {
        projectService.updateProject(projectId, dto);
        projectService.buildTotalService(projectId);
        return response.success(ResponseCode.PROJECT_BUILD_SUCCESS);
    }

    @Operation(summary = "프로젝트 삭제", description = "프로젝트 삭제")
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProject(projectId);

        return response.success(ResponseCode.PROJECT_DELETE_SUCCESS);
    }


    @Operation(summary = "프로젝트 실행", description = "dockerfile, compose 파일 바탕으로 프로젝트 빌드 후 실행")
    @PostMapping("/run/{projectId}")
    public ResponseEntity<?> runProject(@PathVariable String projectId) {
        try {
            projectService.runProject(projectId);
            Thread.sleep(10000);
            if (projectService.verifyComposeUpSuccess(projectId)) {
                return response.success(ResponseCode.PROJECT_RUN_SUCCESS);
            }else{
                throw new ProjectStartFailedException("Run Failed");
            }
        } catch (Exception e) {
            throw new ProjectStartFailedException(e.getMessage());
        }

    }


    @Operation(summary = "프로젝트 일괄 정지", description = "실행중인 프로젝트를 정지")
    @PostMapping("/stop/{projectId}")
    public ResponseEntity<?> stopProject(@PathVariable String projectId) {
        projectService.stopProject(projectId);
        return response.success(ResponseCode.PROJECT_STOP_SUCCESS);
    }

//    @Operation(summary = "서비스 개별 정지", description = "프로젝트 내 서비스 개별 정지")
//    @PostMapping("/stop/{projectId}/{serviceId}")
//    public ResponseEntity<?> stopServiceInProject(@PathVariable String projectId, @RequestParam String containerName) {
//        projectService.stopService(containerName);
//    }
}

