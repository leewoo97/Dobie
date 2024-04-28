package com.dobie.backend.domain.project.service;

import com.dobie.backend.domain.project.dto.ProjectRequestDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;
import com.dobie.backend.domain.project.entity.Project;
import com.dobie.backend.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProjectService {

    private final ProjectRepository projectRepository;

    private int cnt_create = 1;

    public void createProject(ProjectRequestDto dto) {
        Project project = new Project(cnt_create++, dto);
        projectRepository.upsertProject(project);
    }

    public List<ProjectGetResponseDto> getAllProjects() {
        Map<Integer, Project> map = projectRepository.getAllProjects();
        List<ProjectGetResponseDto> list = new ArrayList<>();
        map.forEach((key,value) ->{
            list.add(new ProjectGetResponseDto(value));
        });
        return list;
    }

    public void updateProject(int projectId, ProjectRequestDto dto) {
        Project project = new Project(projectId, dto);
        projectRepository.upsertProject(project);
    }

    public void deleteProject(int projectId) {
        projectRepository.deleteProject(projectId);
    }
}
