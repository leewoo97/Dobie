package com.dobie.backend.domain.project.controller;

import com.dobie.backend.domain.project.dto.ProjectRequestDto;
import com.dobie.backend.domain.project.dto.ProjectGetResponseDto;
import com.dobie.backend.domain.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService  projectService;

    @GetMapping("")
    public ResponseEntity<?> getAllProjects(){
        Map<String, ProjectGetResponseDto> map = projectService.getAllProjects();
        return new ResponseEntity<Map<String, ProjectGetResponseDto>>(map, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequestDto dto){
        projectService.createProject(dto);

        return new ResponseEntity<ProjectRequestDto>(dto, HttpStatus.OK);
    }

    @PutMapping("/update/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable String projectId, @RequestBody ProjectRequestDto dto){
        projectService.updateProject(projectId, dto);

        return new ResponseEntity<ProjectRequestDto>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{projectId}")
    public  ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProject(projectId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/build/{projectId}")
    public ResponseEntity<?> buildTotalProject(@PathVariable String projectId, @RequestBody ProjectRequestDto dto){
        projectService.buildTotalService(projectId, dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/run/{projectId}")
    public ResponseEntity<?> runProject(@PathVariable String projectId){
        projectService.runProject(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/build/{projectId}")
    public ResponseEntity<?> getTotalBuild(@PathVariable String projectId){
        System.out.println("연결확인용 컨트롤러");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

