package com.dobie.backend.domain.project.service;

import com.dobie.backend.domain.project.dto.*;
import com.dobie.backend.domain.project.entity.Backend;
import com.dobie.backend.domain.project.entity.Database;
import com.dobie.backend.domain.project.entity.Frontend;
import com.dobie.backend.domain.project.entity.Project;
import com.dobie.backend.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void createProject(ProjectRequestDto dto) {
        Project project = new Project(UUID.randomUUID().toString(), dto);
        projectRepository.upsertProject(project);
    }

    public Map<String, ProjectGetResponseDto> getAllProjects() {
        Map<String, Project> map = projectRepository.selectProjects();
        Map<String, ProjectGetResponseDto> resultMap = new HashMap<>();
        map.forEach((key,value) ->{
            resultMap.put(key, new ProjectGetResponseDto(value));
        });
        return resultMap;
    }

    public ProjectGetResponseDto getProject(String projectId){
        Project project = projectRepository.searchProject(projectId);
        return new ProjectGetResponseDto(project);
    }

    public List<BackendGetResponseDto> getAllBackends(String projectId){
        Map<String, Backend> backendMap = projectRepository.selectBackends(projectId);
        List<BackendGetResponseDto> list = new ArrayList<>();
        backendMap.forEach((key, value) ->{
            list.add(new BackendGetResponseDto(value));
        });
        return list;
    }

    public BackendGetResponseDto getBackend(String projectId, String serviceId){
        Backend backend = projectRepository.searchBackend(projectId, serviceId);
        return new BackendGetResponseDto(backend);
    }

    public FrontendGetResponseDto getFrontend(String projectId){
        Frontend frontend = projectRepository.searchFrontend(projectId);
        return new FrontendGetResponseDto(frontend);
    }

    public DatabaseGetResponseDto getDatabase(String projectId, String databaseId){
        Database database = projectRepository.searchDatabase(projectId, databaseId);
        return new DatabaseGetResponseDto(database);
    }

    public Map<String, DatabaseGetResponseDto> getAllDatabases(String projectId){
        Map<String, Database> databaseMap = projectRepository.selectDatabases(projectId);
        Map<String, DatabaseGetResponseDto> dtoMap = new HashMap<>();
        databaseMap.forEach((key, value) -> {
            dtoMap.put(key, new DatabaseGetResponseDto(value));
        });
        return dtoMap;
    }

    public void updateProject(String projectId, ProjectRequestDto dto) {
        Project project = new Project(projectId, dto);
        projectRepository.upsertProject(project);
    }

    public void deleteProject(String projectId) {
        projectRepository.deleteProject(projectId);
    }
}
