package com.dobie.backend.domain.project.controller;

import com.dobie.backend.domain.project.dto.ProjectRequestDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;
import com.dobie.backend.domain.project.dto.file.FileGetDto;
import com.dobie.backend.domain.project.dto.file.FilePostDto;
import com.dobie.backend.domain.project.dto.file.FilePutDto;
import com.dobie.backend.domain.project.service.ProjectService;
import com.dobie.backend.exception.format.code.ApiResponse;
import com.dobie.backend.exception.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

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
    @PutMapping("/update")
    public ResponseEntity<?> updateProject(@RequestBody ProjectRequestDto dto) {
        projectService.updateProject(dto);

        return response.success(ResponseCode.PROJECT_INFO_UPDATED, dto);
    }

    @Operation(summary = "프로젝트 빌드", description = "등록된 프로젝트(백, 프론트, db) 정보를 바탕으로 build파일 생성")
    @PostMapping("/build/{projectId}")
    public ResponseEntity<?> buildTotalProject(@PathVariable String projectId) {
        projectService.buildTotalService(projectId);

        return response.success(ResponseCode.PROJECT_BUILD_SUCCESS);
    }

    @Operation(summary = "프로젝트 등록, 빌드", description = "프로젝트 정보를 등록한 후 정보 기반으로 빌드파일 생성")
    @PostMapping("/regist")
    public ResponseEntity<?> registerProject(@RequestBody ProjectRequestDto dto) {
        String projectId = projectService.createProject(dto);
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
        projectService.runProject(projectId);
        return response.success(ResponseCode.PROJECT_RUN_SUCCESS);
    }


    @Operation(summary = "프로젝트 일괄 정지", description = "실행중인 프로젝트를 정지")
    @PostMapping("/stop/{projectId}")
    public ResponseEntity<?> stopProject(@PathVariable String projectId) {
        projectService.stopProject(projectId);
        return response.success(ResponseCode.PROJECT_STOP_SUCCESS);
    }

    @Operation(summary = "서비스 개별 정지", description = "프로젝트 내 서비스 개별 정지")
    @PostMapping("/stop/service")
    public ResponseEntity<?> stopServiceInProject(@RequestParam String containerName) {
        projectService.stopService(containerName);
        return response.success(ResponseCode.SERVICE_STOP_SUCCESS);
    }

    @Operation(summary = "서비스 개별 시작", description = "프로젝트 시작후 정지한 서비스 개별 시작")
    @PostMapping("/start/service")
    public ResponseEntity<?> startServiceInProject(@RequestParam String containerName) {
        projectService.startService(containerName);
        return response.success(ResponseCode.SERVICE_START_SUCCESS);
    }

    @Operation(summary = "프로젝트 재시작", description = "webhook 요청 시 프로젝트 재빌드, 시작")
    @PostMapping("/webhook/{projectId}")
    public ResponseEntity<?> restartProject(@PathVariable String projectId) {
        projectService.rebuildAndStartProject(projectId);
        return response.success(ResponseCode.PROJECT_REBUILD_AND_START_SUCCESS);
    }

    @Operation(summary = "프로젝트 환경설정 파일 추가", description = "gitignore에 존재하는 파일 첨부")
    @PostMapping(value="/file", consumes = "multipart/form-data")
    public ResponseEntity<?> addFile(@RequestPart FilePostDto dto, @RequestPart("files") List<MultipartFile> files) {
        projectService.addFile(dto, files);

        return response.success(ResponseCode.FILE_UPLOAD_SUCCESS);
    }

    @Operation(summary = "프로젝트 환경설정 파일 조회", description = "gitignore에 존재하는 파일 첨부")
    @GetMapping("/file/{projectId}")
    public ResponseEntity<?> getFile(@PathVariable String projectId) {
        List<FileGetDto> fileMap = projectService.getFile(projectId);
        return response.success(ResponseCode.FILE_LIST_FETCHED, fileMap);
    }

    @Operation(summary = "프로젝트 환경설정 파일 삭제", description = "첨부한 파일 삭제")
    @PutMapping(value="/file")
    public ResponseEntity<?> deleteFile(@RequestBody FilePutDto dto) {
        projectService.deleteFile(dto);
        return response.success(ResponseCode.FILE_DELETE_SUCCESS);
    }

    @Operation(summary = "파일첨부(리스트) 테스트", description = "gitignore에 존재하는 파일 첨부")
    @PostMapping(value="/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadFile(@RequestParam("files") List<MultipartFile> files) {
        if (files.isEmpty()) {
            return ResponseEntity.status(400).body("Please upload a file!");
        }

        for(MultipartFile file : files){
            System.out.println("파일도착");
            System.out.println(file.getOriginalFilename());
            try (InputStream inputStream = file.getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException e) {
                return ResponseEntity.status(500).body("Failed to read file: " + e.getMessage());
            }
            System.out.println();
        }

        return response.success(ResponseCode.FILE_UPLOAD_SUCCESS);
    }

}

