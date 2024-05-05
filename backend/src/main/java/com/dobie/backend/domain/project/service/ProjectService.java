package com.dobie.backend.domain.project.service;

import com.dobie.backend.domain.docker.dockercompose.service.DockerComposeService;
import com.dobie.backend.domain.docker.dockerfile.service.DockerfileService;
import com.dobie.backend.domain.nginx.service.NginxConfigService;
import com.dobie.backend.domain.project.dto.*;
import com.dobie.backend.domain.project.entity.Backend;
import com.dobie.backend.domain.project.entity.Database;
import com.dobie.backend.domain.project.entity.Frontend;
import com.dobie.backend.domain.project.entity.Project;
import com.dobie.backend.domain.project.repository.ProjectRepository;
import com.dobie.backend.exception.exception.build.GitInfoNotFoundException;
import com.dobie.backend.util.command.CommandService;

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

