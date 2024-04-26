package com.dobie.backend.domain.project.entity;

import com.dobie.backend.domain.project.dto.ProjectRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private int projectId;
    private String projectName;

    private Git git;
    private Map<String, Backend> backendMap;
    private Frontend frontend;
    private Database database;

    public Project(int projectId, ProjectRequestDto dto){
        this.projectId = projectId;
        this.projectName = dto.getProjectName();
        this.git = new Git(dto.getGit());
        this.backendMap = new HashMap<>();
        dto.getBackendMap().forEach((key, value) -> {
            this.backendMap.put(key, new Backend(Integer.parseInt(key), value));
        });
        this.frontend = new Frontend(dto.getFrontend());
        this.database = new Database(dto.getDatabase());
    }
}
