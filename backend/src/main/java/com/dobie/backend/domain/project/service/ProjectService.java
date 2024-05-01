package com.dobie.backend.domain.project.service;

import com.dobie.backend.domain.docker.dockercompose.service.DockerComposeService;
import com.dobie.backend.domain.docker.dockerfile.service.DockerfileService;
import com.dobie.backend.domain.project.dto.*;
import com.dobie.backend.domain.project.entity.Backend;
import com.dobie.backend.domain.project.entity.Database;
import com.dobie.backend.domain.project.entity.Frontend;
import com.dobie.backend.domain.project.entity.Project;
import com.dobie.backend.domain.project.repository.ProjectRepository;
import com.dobie.backend.util.command.CommandService;
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
    private final CommandService commandService;
    private final DockerfileService dockerfileService;
    private final DockerComposeService dockerComposeService;

    private int cnt_create = 1;

    public void createProject(ProjectRequestDto dto) {
        Project project = new Project(cnt_create++, dto);
        projectRepository.upsertProject(project);
    }

    public List<ProjectGetResponseDto> getAllProjects() {
        Map<Integer, Project> map = projectRepository.selectProjects();
        List<ProjectGetResponseDto> list = new ArrayList<>();
        map.forEach((key, value) -> {
            list.add(new ProjectGetResponseDto(value));
        });
        return list;
    }

    public ProjectGetResponseDto getProject(int projectId) {
        Project project = projectRepository.searchProject(projectId);
        return new ProjectGetResponseDto(project);
    }

    public List<BackendGetResponseDto> getAllBackends(int projectId) {
        Map<String, Backend> backendMap = projectRepository.selectBackends(projectId);
        List<BackendGetResponseDto> list = new ArrayList<>();
        backendMap.forEach((key, value) -> {
            list.add(new BackendGetResponseDto(value));
        });
        return list;
    }

    public BackendGetResponseDto getBackend(int projectId, int serviceId) {
        Backend backend = projectRepository.searchBackend(projectId, serviceId);
        return new BackendGetResponseDto(backend);
    }

    public FrontendGetResponseDto getFrontend(int projectId) {
        Frontend frontend = projectRepository.searchFrontend(projectId);
        return new FrontendGetResponseDto(frontend);
    }

    public DatabaseGetResponseDto getDatabase(int projectId) {
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

    /*
     * 프로젝트 실행 관련 메서드들
     * */


    // 전체 프로젝트(main 브랜치에서 한번에 관리) 빌드 메서드
    // 사실상 dockerfile이랑 compose file 넣어놓는 용도
    public void buildTotalService(int projectId, ProjectRequestDto dto) {
        Project project = projectRepository.searchProject(projectId);

        // git clone
        GitRequestDto gitInfo = dto.getGit();
        // git type 확인, gitLab인지 gitHub인지
        // 1이면 gitLab
        if (gitInfo.getGitType() == 1) {
            // gitLab clone
            commandService.gitCloneGitLab(gitInfo.getGitUrl(), gitInfo.getAccessToken());
        } else {
            // gitHub Clone
            commandService.gitClone(gitInfo.getGitUrl());
        }

        // dockerfile 생성
        // 백엔드
        Map<String, BackendRequestDto> backendInfo = dto.getBackendMap();
        backendInfo.forEach((key, value) -> {
            if(value.getFramework() == "Spring"){
                dockerfileService.createGradleDockerfile(value.getServiceName(), value.getVersion(), value.getPath());
            }else {

            }
        });

        // 프론트엔드
        FrontendRequestDto frontendInfo = dto.getFrontend();
        if(frontendInfo.getFramework() == "React"){
            dockerfileService.createReactDockerfile(frontendInfo.getServiceName(), frontendInfo.getVersion(), frontendInfo.getPath() );
        }

        // docker-compose 파일 생성
        ProjectGetResponseDto projectGetResponseDto = new ProjectGetResponseDto(project);
        dockerComposeService.createDockerComposeFile(projectGetResponseDto);
    }

    // 프론트 개별 빌드
    void buildFrontService(int projectId, ProjectRequestDto dto) {

        Project project = projectRepository.searchProject(projectId);

        // git clone
        GitRequestDto gitInfo = dto.getGit();
        // git type 확인, gitLab인지 gitHub인지
        // 1이면 gitLab
        if (gitInfo.getGitType() == 1) {
            // gitLab clone
            commandService.gitCloneGitLab(gitInfo.getGitUrl(), gitInfo.getAccessToken());
        } else {
            // gitHub Clone
            commandService.gitClone(gitInfo.getGitUrl());
        }

        FrontendRequestDto frontendInfo = dto.getFrontend();

        // checkout
        commandService.gitCheckout(frontendInfo.getPath(), frontendInfo.getBranch());

        // dockerfile
        if(frontendInfo.getFramework() == "React"){
            dockerfileService.createReactDockerfile(frontendInfo.getServiceName(), frontendInfo.getVersion(), frontendInfo.getPath() );
        }

        // docker-compose
        ProjectGetResponseDto projectGetResponseDto = new ProjectGetResponseDto(project);
        dockerComposeService.createDockerComposeFile(projectGetResponseDto);
    }

    // 백엔드 개별 빌드
    void buildBackService(int projectId, ProjectRequestDto dto) {

        Project project = projectRepository.searchProject(projectId);

        GitRequestDto gitInfo = dto.getGit();
        if (gitInfo.getGitType() == 1) {
            commandService.gitCloneGitLab(gitInfo.getGitUrl(), gitInfo.getAccessToken());
        }else {
            commandService.gitClone(gitInfo.getGitUrl());
        }

        Map<String, BackendRequestDto> backendInfo = dto.getBackendMap();
        backendInfo.forEach((key, value) -> {

            // git checkout
            commandService.gitCheckout(value.getPath(), value.getBranch());

            // dockerfile
            // home/projectName+path
            if(value.getFramework() == "Spring"){
                dockerfileService.createGradleDockerfile(project.getProjectName(), value.getVersion(), value.getPath());
            }
        });

        // docker compose 파일 생성 -> 이거 좀 햇갈리는게 각각 실행시키고 백엔드 서비스가 여러개 있을때 docker-compose는 여러개인가 하나인가
        ProjectGetResponseDto projectGetResponseDto = new ProjectGetResponseDto(project);
        dockerComposeService.createDockerComposeFile(projectGetResponseDto);
    }

    // 프로젝트 통째로 실행한다 했을때
    void runProject(int projectId) {
        Project project = projectRepository.searchProject(projectId);

        // git clone 받으면 projectName으로 폴더가 생성되어 있을테니
        String path = "/"+project.getProjectName();
        commandService.dockerComposeUp(path);
    }
}

