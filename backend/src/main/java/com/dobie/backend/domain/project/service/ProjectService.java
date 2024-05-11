package com.dobie.backend.domain.project.service;

import com.dobie.backend.domain.project.dto.*;

import java.util.*;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {


    String createProject(ProjectRequestDto dto);

    Map<String, ProjectGetResponseDto> getAllProjects();

    ProjectGetResponseDto getProject(String projectId);

    List<BackendGetResponseDto> getAllBackends(String projectId);

    BackendGetResponseDto getBackend(String projectId, String serviceId);

    FrontendGetResponseDto getFrontend(String projectId);

    DatabaseGetResponseDto getDatabase(String projectId, String databaseId);

    Map<String, DatabaseGetResponseDto> getAllDatabases(String projectId);

    void updateProject(String projectId, ProjectRequestDto dto);

    void deleteProject(String projectId);

    void buildTotalService(String projectId);

    void runProject(String projectId);

    void stopProject(String projectId);

    void stopService(String containerName);

    void startService(String containerName);

    boolean verifyComposeUpSuccess(String path);

    void rebuildAndStartProject(String projectId);

    String createProjectWithFile(ProjectWithFileRequestDto dto, List<MultipartFile> files);

    void buildTotalServiceWithFile(String projectId, List<String> filePathList, List<MultipartFile> files);

//    void buildFrontService(String projectId, ProjectRequestDto dto)

//    void buildBackService(String projectId, ProjectRequestDto dto)

}

