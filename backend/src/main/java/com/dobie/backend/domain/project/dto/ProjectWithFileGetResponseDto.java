package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Project;
import com.dobie.backend.domain.project.entity.ProjectWithFile;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectWithFileGetResponseDto {
    private String projectId;
    private String projectName;

    private String projectDomain;
    private boolean usingHttps;

    private GitGetResponseDto git;
    private Map<String, BackendGetResponseDto> backendMap;
    private FrontendGetResponseDto frontend;
    private Map<String, DatabaseGetResponseDto> databaseMap;
    private Map<String, FileGetResponseDto> fileMap;

    public ProjectWithFileGetResponseDto(ProjectWithFile project){
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
        this.fileMap = new HashMap<>();
        project.getFileMap().forEach((key, value) -> {
            fileMap.put(key, new FileGetResponseDto(value));
        });
    }
    
}
