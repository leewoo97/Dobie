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

    private Git git;
    private Map<String, Backend> backendMap;
    private Frontend frontend;
    private Map<String, Database> databaseMap;

    public Project(String projectId, ProjectRequestDto dto){
        this.projectId = projectId;
        this.projectName = dto.getProjectName();
        this.git = new Git(dto.getGit());
        this.backendMap = new HashMap<>();
        dto.getBackendMap().forEach((key, value) -> {
            this.backendMap.put(key, new Backend(UUID.randomUUID().toString(), value));
        });
        this.frontend = new Frontend(dto.getFrontend());
        this.databaseMap = new HashMap<>();
        dto.getDatabaseMap().forEach((key,value) -> {
            this.databaseMap.put(key, new Database(UUID.randomUUID().toString(), value));
        });

    }
}
