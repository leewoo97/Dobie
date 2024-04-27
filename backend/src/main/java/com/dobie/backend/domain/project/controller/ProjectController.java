package com.dobie.backend.domain.project.controller;

import com.dobie.backend.domain.project.dto.ProjectRequestDto;
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

    @GetMapping("/")
    public ResponseEntity<?> getAllProjects(){
        List<ProjectGetResponseDto> list = projectService.getAllProjects();
        return new ResponseEntity<List<ProjectGetResponseDto>>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequestDto dto){
        projectService.createProject(dto);

        return new ResponseEntity<ProjectRequestDto>(dto, HttpStatus.OK);
    }

    @PutMapping("/update/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable int projectId, @RequestBody ProjectRequestDto dto){
        projectService.updateProject(projectId, dto);

        return new ResponseEntity<ProjectRequestDto>(dto, HttpStatus.OK);
    }
}
