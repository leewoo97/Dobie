package com.dobie.backend.domain.project.service;

import com.dobie.backend.domain.project.dto.ProjectCreateDto;
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

    public void createProject(ProjectCreateDto dto) {
        projectRepository.createProject(dto);
    }

    public List<ProjectGetResponseDto> getAllProjects() {
        Map<Integer, Project> map = projectRepository.getAllProjects();
        List<ProjectGetResponseDto> list = new ArrayList<>();
        for(Project p : map.values()){
            list.add(new ProjectGetResponseDto(p.getProjectId(), p.getProjectName()));
        }
        return list;
    }
}
