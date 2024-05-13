package com.dobie.backend.domain.project.entity;

import java.util.Map;
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
    private Map<String, SettingFile> fileMap;

    public ProjectWithFile(Project project, Map<String, SettingFile> fileMap){
        this.projectId = project.getProjectId();
        this.projectName = project.getProjectName();
        this.projectDomain = project.getProjectDomain();
        this.usingHttps = project.isUsingHttps();

        this.git = project.getGit();
        this.backendMap = project.getBackendMap();
        this.frontend = project.getFrontend();
        this.databaseMap = project.getDatabaseMap();
        this.fileMap = fileMap;
    }

//    public ProjectWithFile(String projectId, ProjectWithFileRequestDto dto){
//        this.projectId = projectId;
//        this.projectName = dto.getProjectName();
//
//        this.projectDomain = dto.getProjectDomain();
//        this.usingHttps = dto.isUsingHttps();
//
//        this.git = new Git(dto.getGit());
//        this.backendMap = new HashMap<>();
//        dto.getBackendMap().forEach((key, value) -> {
//            String uuid = UUID.randomUUID().toString();
//            this.backendMap.put(uuid, new Backend(uuid, value));
//        });
//        this.frontend = new Frontend(UUID.randomUUID().toString(), dto.getFrontend());
//        this.databaseMap = new HashMap<>();
//        dto.getDatabaseMap().forEach((key,value) -> {
//            String uuid = UUID.randomUUID().toString();
//            this.databaseMap.put(uuid, new Database(uuid, value));
//        });
//        this.fileMap = new HashMap<>();
//        dto.getFileMap().forEach((key, value) -> {
//            String uuid = UUID.randomUUID().toString();
//            this.fileMap.put(uuid, new File(uuid, value));
//        });
//    }

}
