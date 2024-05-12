package com.dobie.backend.domain.project.entity;

import com.dobie.backend.domain.project.dto.FileRequestDto;
import com.dobie.backend.domain.project.dto.ProjectRequestDto;
import com.dobie.backend.domain.project.dto.ProjectWithFileRequestDto;
import com.dobie.backend.domain.project.dto.ProjectWithFileUpdateRequestDto;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWithFile {
    private String projectId;
    private String projectName;

    private String projectDomain;
    private boolean usingHttps;

    private Git git;
    private Map<String, Backend> backendMap;
    private Frontend frontend;
    private Map<String, Database> databaseMap;
    private Map<String, File> fileMap;

    public ProjectWithFile(String projectId, ProjectWithFileRequestDto dto, Map<String, FileRequestDto> fileMap){
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
        this.fileMap = new HashMap<>();
        fileMap.forEach((key, value) -> {
            String uuid = UUID.randomUUID().toString();
            this.fileMap.put(uuid, new File(uuid, value));
        });
    }

    public ProjectWithFile(String projectId, ProjectWithFileUpdateRequestDto dto, Map<String, FileRequestDto> fileMap){
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
        this.fileMap = new HashMap<>();
        fileMap.forEach((key, value) -> {
            String uuid = UUID.randomUUID().toString();
            this.fileMap.put(uuid, new File(uuid, value));
        });
    }
}
