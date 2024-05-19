package com.dobie.backend.domain.project.service;

import com.dobie.backend.domain.project.dto.*;

import com.dobie.backend.domain.project.dto.file.FileGetDto;
import com.dobie.backend.domain.project.dto.file.FilePostDto;
import com.dobie.backend.domain.project.dto.file.FilePutDto;
import com.dobie.backend.domain.project.entity.SettingFile;
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

    Map<String, SettingFile> getAllFiles(String projectId);

    void updateProject(ProjectRequestDto dto);

    void deleteProject(String projectId);

    void buildTotalService(String projectId);

    void runProject(String projectId);

    void stopProject(String projectId);

    void stopService(String containerName);

    void startService(String containerName);

    boolean verifyComposeUpSuccess(String path);

    void rebuildAndStartProject(String projectId);

    void addFile(FilePostDto dto, List<MultipartFile> files);

    List<FileGetDto> getFile(String projectId);

    void deleteFile(FilePutDto dto);

}

