package com.dobie.backend.domain.docker.dockerfile.controller;

import com.dobie.backend.domain.docker.dockerfile.service.DockerfileService;
import com.dobie.backend.domain.docker.readjson.service.ReadJsonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Dockerfile 컨트롤러", description = "Dockerfile Controller API")
@RestController
@RequestMapping("/api/dockerfile")
@RequiredArgsConstructor
public class DockerfileController {

    final DockerfileService dockerfileService;
    final ReadJsonService readJsonService;

    @Operation(summary = "스프링 도커파일 생성", description = "프로젝트이름, 언어버전, 경로")
    @PostMapping("/spring")
    public ResponseEntity<?> springDockerfile(@RequestParam(name = "projectName") String projectName,
                                               @RequestParam(name = "version") String version,
                                               @RequestParam(name = "path") String path){
        dockerfileService.createGradleDockerfile(projectName, version, path);
        return new ResponseEntity<String>("성공", HttpStatus.OK);
    }

    @Operation(summary = "리액트 도커파일 생성", description = "프로젝트이름, 언어버전, 경로")
    @PostMapping("/react")
    public ResponseEntity<?> reactDockerfile(@RequestParam(name = "projectName") String projectName,
                                               @RequestParam(name = "version") String version,
                                               @RequestParam(name = "path") String path,
                                               @RequestParam(name = "usingNginx") boolean usingNginx){
        dockerfileService.createReactDockerfile(projectName, version, path , usingNginx);
        return new ResponseEntity<String>("성공", HttpStatus.OK);
    }

    @Operation(summary = "도커파일 위치 조회(project.json으로 경로파악해서 조회)", description = "프로젝트 아이디, 서비스아이디, 서비스 타입을 분석해서 도커파일의 경로를 알아냅니다.")
    @GetMapping("/dockerfile-path")
    public ResponseEntity<?> readDockerfileContent(@RequestParam(name = "projectId") String projectId,
                                                   @RequestParam(name = "serviceId") String serviceId,
                                                   @RequestParam(name = "type") String type){
        String filepath = dockerfileService.makeDockerfilePathContent(projectId, serviceId, type);
        return new ResponseEntity<>(filepath,HttpStatus.OK);
    }

    @Operation(summary = "도커파일 내용 조회(파일 위치 파악 후 진행)", description = "파일의 내용을 조회합니다.")
    @GetMapping("/dockerfile-content")
    public ResponseEntity<?> readEnvironmentfileContent(@RequestParam(name = "projectId") String projectId,
                                                        @RequestParam(name = "serviceId") String serviceId,
                                                        @RequestParam(name = "type") String type){
        String filepath = dockerfileService.makeDockerfilePathContent(projectId, serviceId, type);
        String content = dockerfileService.readEnvironmentDockerFile(filepath);
        return new ResponseEntity<>(content,HttpStatus.OK);
    }

    @Operation(summary = "도커컴포즈 파일 위치 조회(project.json으로 경로파악해서 조회)", description = "프로젝트 아이디를 분석해서 도커파일의 경로를 알아냅니다.")
    @GetMapping("/docker-compose-file-path")
    public ResponseEntity<?> readDockerComposefileContent(@RequestParam(name = "projectId") String projectId){
        String filepath = dockerfileService.makeDockerComposefilePathContent(projectId);
        return new ResponseEntity<>(filepath,HttpStatus.OK);
    }

    @Operation(summary = "도커컴포즈파일 내용 조회(파일 위치 파악 후 진행)", description = "파일의 내용을 조회합니다.")
    @GetMapping("/docker-compose-file-content")
    public ResponseEntity<?> readEnvironmentfileContent(@RequestParam(name = "projectId") String projectId){
        String filepath = dockerfileService.makeDockerComposefilePathContent(projectId);
        String content = dockerfileService.readEnvironmentDockerComposeFile(filepath);
        return new ResponseEntity<>(content,HttpStatus.OK);
    }

    @Operation(summary = "컨테이너 로그 내용 조회", description = "컨테이너 로그를 조회합니다.")
    @GetMapping("/docker-container-logs")
    public ResponseEntity<?> readContainerLogContent(@RequestParam(name = "mountId") String mountId){
        //mountId는 serviceId또는 databaseId를 의미합니다.
        String content = dockerfileService.readContainerLog(mountId);
        return new ResponseEntity<>(content,HttpStatus.OK);
    }
}
