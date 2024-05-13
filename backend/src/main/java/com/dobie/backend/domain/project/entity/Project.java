package com.dobie.backend.domain.project.entity;

import com.dobie.backend.domain.project.dto.ProjectRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String projectId;
    private String projectName;

    private String projectDomain;
    private boolean usingHttps;

    private Git git;
    private Map<String, Backend> backendMap;
    private Frontend frontend;
    private Map<String, Database> databaseMap;

    public Project(String projectId, ProjectRequestDto dto){
        this.projectId = projectId;
        this.projectName = dto.getProjectName();

        this.projectDomain = dto.getProjectDomain();
        this.usingHttps = dto.isUsingHttps();

        this.git = new Git(dto.getGit());
        this.backendMap = new HashMap<>();
        dto.getBackendMap().forEach((key, value) -> {
            String uuid = UUID.randomUUID().toString();
            this.backendMap.put(uuid, new Backend(uuid, value));
        });
        this.frontend = new Frontend(UUID.randomUUID().toString(), dto.getFrontend());
        this.databaseMap = new HashMap<>();
        dto.getDatabaseMap().forEach((key,value) -> {
            String uuid = UUID.randomUUID().toString();
            this.databaseMap.put(uuid, new Database(uuid, value));
        });

    }

    public Project(ProjectWithFile projectWithFile){
        this.projectId = projectWithFile.getProjectId();
        this.projectName = projectWithFile.getProjectName();

        this.projectDomain = projectWithFile.getProjectDomain();
        this.usingHttps = projectWithFile.isUsingHttps();

        this.git = projectWithFile.getGit();
        this.backendMap = projectWithFile.getBackendMap();
        this.frontend = projectWithFile.getFrontend();
        this.databaseMap = projectWithFile.getDatabaseMap();
    }
}
