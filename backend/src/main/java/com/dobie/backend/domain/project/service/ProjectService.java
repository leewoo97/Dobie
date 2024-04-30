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
        Map<Integer, Project> map = projectRepository.selectProjects();
        List<ProjectGetResponseDto> list = new ArrayList<>();
        map.forEach((key,value) ->{
            list.add(new ProjectGetResponseDto(value));
        });
        return list;
    }

    public ProjectGetResponseDto getProject(int projectId){
        Project project = projectRepository.searchProject(projectId);
        return new ProjectGetResponseDto(project);
    }

    public List<BackendGetResponseDto> getAllBackends(int projectId){
        Map<String, Backend> backendMap = projectRepository.selectBackends(projectId);
        List<BackendGetResponseDto> list = new ArrayList<>();
        backendMap.forEach((key, value) ->{
            list.add(new BackendGetResponseDto(value));
        });
        return list;
    }

    public BackendGetResponseDto getBackend(int projectId, int serviceId){
        Backend backend = projectRepository.searchBackend(projectId, serviceId);
        return new BackendGetResponseDto(backend);
    }

    public FrontendGetResponseDto getFrontend(int projectId){
        Frontend frontend = projectRepository.searchFrontend(projectId);
        return new FrontendGetResponseDto(frontend);
    }

    public DatabaseGetResponseDto getDatabase(int projectId){
        Database database = projectRepository.searchDatabase(projectId);
        return new DatabaseGetResponseDto(database);
    }

    public void updateProject(int projectId, ProjectRequestDto dto) {
        Project project = new Project(projectId, dto);
        projectRepository.upsertProject(project);
    }

    public void deleteProject(int projectId) {
        projectRepository.deleteProject(projectId);
    }

    public NginxConfigDto getNginxConfigDto(int projectId){
        // 프로젝트 찾기
        Project project = projectRepository.searchProject(projectId);
        
        // NginxConfigDto 생성
        NginxConfigDto dto = NginxConfigDto.builder()
                .domain(project.getProjectDomain())
                .usingHttps(project.isUsingHttps())
                .sslCertificate("")
                .sslCertificateKey("")
                .build();
        
        // Proxy list 생성
        List<NginxProxyDto> proxyList = new ArrayList<>();
        
        // backend -> proxy
        project.getBackendMap().forEach((key, backend) -> {
            proxyList.add(new NginxProxyDto(backend));
        });
        
        // frontend -> proxy
        proxyList.add(new NginxProxyDto(project.getFrontend()));
        
        // proxyList 저장
        dto.setProxyList(proxyList);

        return dto;
    }
}
