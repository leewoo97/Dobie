package com.dobie.backend.domain.project.service;

import com.dobie.backend.domain.project.dto.*;

import java.util.*;

public interface ProjectService {


    void createProject(ProjectRequestDto dto);

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

//    void buildFrontService(String projectId, ProjectRequestDto dto)

//    void buildBackService(String projectId, ProjectRequestDto dto)

}

