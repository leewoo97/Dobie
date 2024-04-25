package com.dobie.backend.domain.project.controller;

import com.dobie.backend.domain.project.dto.ProjectCreateDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;
import com.dobie.backend.domain.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService  projectService;

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectCreateDto dto){
        projectService.createProject(dto);

        return new ResponseEntity<ProjectCreateDto>(dto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProjects(){
        List<ProjectGetResponseDto> list = projectService.getAllProjects();
        return new ResponseEntity<List<ProjectGetResponseDto>>(list, HttpStatus.OK);
    }
}
