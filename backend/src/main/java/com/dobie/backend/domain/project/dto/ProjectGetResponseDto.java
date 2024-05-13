package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Project;
import com.dobie.backend.domain.project.entity.ProjectWithFile;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectGetResponseDto {
    private String projectId;
    private String projectName;

    private String projectDomain;
    private boolean usingHttps;

    private GitGetResponseDto git;
    private Map<String, BackendGetResponseDto> backendMap;
    private FrontendGetResponseDto frontend;
    private Map<String, DatabaseGetResponseDto> databaseMap;

    public ProjectGetResponseDto(Project project){
        this.projectId = project.getProjectId();
        this.projectName = project.getProjectName();

        this.projectDomain = project.getProjectDomain();
        this.usingHttps = project.isUsingHttps();

        this.git = new GitGetResponseDto(project.getGit());
        this.backendMap = new HashMap<>();
        project.getBackendMap().forEach((key,value) -> {
            backendMap.put(key, new BackendGetResponseDto(value));
        });
        this.frontend = new FrontendGetResponseDto(project.getFrontend());
        this.databaseMap = new HashMap<>();
        project.getDatabaseMap().forEach((key,value) -> {
            databaseMap.put(key, new DatabaseGetResponseDto(value));
        });
    }

    public ProjectGetResponseDto(ProjectWithFile project){
        this.projectId = project.getProjectId();
        this.projectName = project.getProjectName();

        this.projectDomain = project.getProjectDomain();
        this.usingHttps = project.isUsingHttps();

        this.git = new GitGetResponseDto(project.getGit());
        this.backendMap = new HashMap<>();
        project.getBackendMap().forEach((key,value) -> {
            backendMap.put(key, new BackendGetResponseDto(value));
        });
        this.frontend = new FrontendGetResponseDto(project.getFrontend());
        this.databaseMap = new HashMap<>();
        project.getDatabaseMap().forEach((key,value) -> {
            databaseMap.put(key, new DatabaseGetResponseDto(value));
        });
    }
    
}
