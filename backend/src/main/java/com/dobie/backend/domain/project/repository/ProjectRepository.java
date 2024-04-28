package com.dobie.backend.domain.project.repository;

import com.dobie.backend.domain.project.dto.ProjectRequestDto;
import com.dobie.backend.domain.project.entity.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    private final ObjectMapper mapper;
    public void upsertProject(Project project) {
        try{
            // 파일 읽기
            File file = new File(System.getProperty("user.dir")+"/data/project.json");

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, Project.class);

            // project map 불러오기
            Map<String, Project> projects = mapper.readValue(file, mapType);

            // project 생성
            projects.put(String.valueOf(project.getProjectId()), project);


            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, projects);
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public Map<Integer, Project> getAllProjects() {
        try{
            // 파일 읽기
            File file = new File(System.getProperty("user.dir")+"/data/project.json");

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, Integer.class, Project.class);

            return mapper.readValue(file, mapType);

        }catch (IOException e ){
            e.printStackTrace();
        }

        return null;
    }

    public void deleteProject(int projectId) {
        try{
            // 파일 읽기
            File file = new File(System.getProperty("user.dir")+"/data/project.json");

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, Project.class);

            // project map 불러오기
            Map<String, Project> projects = mapper.readValue(file, mapType);

            // project 삭제
            projects.remove(String.valueOf(projectId));

            // json 파일 작성
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, projects);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
