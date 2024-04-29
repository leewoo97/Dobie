package com.dobie.backend.domain.docker.dockerfile.controller;

import com.dobie.backend.domain.docker.dockerfile.service.DockerfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Dockerfile 컨트롤러", description = "Dockerfile Controller API")
@RestController
@RequestMapping("/dockerfile")
@RequiredArgsConstructor
public class DockerfileController {

    final DockerfileService dockerfileService;

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
                                               @RequestParam(name = "path") String path){
        dockerfileService.createReactDockerfile(projectName, version, path);
        return new ResponseEntity<String>("성공", HttpStatus.OK);
    }
}