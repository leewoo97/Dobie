package com.dobie.backend.domain.project.repository;

import com.dobie.backend.domain.project.dto.file.FileGetDto;
import com.dobie.backend.domain.project.entity.Backend;
import com.dobie.backend.domain.project.entity.Database;
import com.dobie.backend.domain.project.entity.Frontend;
import com.dobie.backend.domain.project.entity.Project;
import com.dobie.backend.domain.project.entity.ProjectWithFile;
import com.dobie.backend.domain.project.entity.SettingFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    private final ObjectMapper mapper;

    private static final String BASE_PATH = new File("").getAbsolutePath();
    private static final String RESOURCE_PATH = "/src/main/resources";
    private static final String FILE_NAME = "/data/project.json";
    private static final String FILE_PATH =
            Files.exists(Paths.get(BASE_PATH + RESOURCE_PATH + FILE_NAME)) ? BASE_PATH + RESOURCE_PATH + FILE_NAME : BASE_PATH + FILE_NAME;
    public void upsertProject(Project project) {
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

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
            e.getStackTrace();
        }
    }

    public void upsertProjectWithFile(Project project, Map<String, SettingFile> fileMap) {
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            // project map 불러오기
            Map<String, ProjectWithFile> projects = mapper.readValue(file, mapType);
            ProjectWithFile projectWithFile = new ProjectWithFile(project, fileMap);

            // project 생성
            projects.put(String.valueOf(project.getProjectId()), projectWithFile);


            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(file, projects);
        }catch (IOException e ){
            e.getStackTrace();
        }
    }

    public Map<String, Project> selectProjects() {
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            return mapper.readValue(file, mapType);

        }catch (IOException e ){
            e.getStackTrace();
        }

        return null;
    }

    public Map<String, ProjectWithFile> selectProjectsWithFile() {
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            return mapper.readValue(file, mapType);

        }catch (IOException e ){
            e.getStackTrace();
        }

        return null;
    }

    public Project searchProject(String projectId){
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, Project.class);

            // projectMap 불러오기
            Map<String, Project> projectMap = mapper.readValue(file, mapType);

            return projectMap.get(projectId);
        } catch (IOException e){
            e.getStackTrace();
        }

        return null;
    }

    public ProjectWithFile searchProjectWithFile(String projectId){
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);
            System.out.println("파일읽기" + projectId);
            // mapper class 지정
            MapType mapType =
                mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            // projectMap 불러오기
            Map<String, ProjectWithFile> projectMap = mapper.readValue(file, mapType);

            return projectMap.get(projectId);
        } catch (IOException e){
            e.getStackTrace();
        }

        return null;
    }

    public Map<String, Backend> selectBackends(String projectId) {
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            // project map 불러오기
            Map<String, ProjectWithFile> projects = mapper.readValue(file, mapType);

            return projects.get(projectId).getBackendMap();
        }catch (IOException e){
            e.getStackTrace();
        }

        return null;
    }

    public Backend searchBackend(String projectId, String serviceId) {
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            // project map 불러오기
            Map<String, ProjectWithFile> projects = mapper.readValue(file, mapType);

            //
            Map<String, Backend> backendMap = projects.get(projectId).getBackendMap();

            return backendMap.get(serviceId);
        }catch (IOException e){
            e.getStackTrace();
        }

        return null;
    }

    public Frontend searchFrontend(String projectId) {
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            // project map 불러오기
            Map<String, ProjectWithFile> projects = mapper.readValue(file, mapType);

            return projects.get(projectId).getFrontend();
        }catch (IOException e){
            e.getStackTrace();
        }

        return null;
    }

    public Map<String, Database> selectDatabases(String projectId){
        try {
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            // project map 불러오기
            Map<String, ProjectWithFile> projects = mapper.readValue(file, mapType);

            return projects.get(projectId).getDatabaseMap();
        }catch (IOException e){
            e.getStackTrace();
        }
        return null;
    }

    public Database searchDatabase(String projectId, String databaseId) {
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            // project map 불러오기
            Map<String, ProjectWithFile> projects = mapper.readValue(file, mapType);

            return projects.get(projectId).getDatabaseMap()
                    .get(databaseId);
        }catch (IOException e){
            e.getStackTrace();
        }

        return null;
    }

    public void deleteProject(String projectId) {
        try{
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            // project map 불러오기
            Map<String, ProjectWithFile> projects = mapper.readValue(file, mapType);

            // project 삭제
            projects.remove(projectId);

            // json 파일 작성
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, projects);

        }catch (IOException e){
            e.getStackTrace();
        }
    }

    public Map<String, SettingFile> selectFiles(String projectId) {
        try {
            // 파일 읽기
            File file = new File(FILE_PATH);

            // mapper class 지정
            MapType mapType =
                mapper.getTypeFactory().constructMapType(Map.class, String.class, ProjectWithFile.class);

            // project map 불러오기
            Map<String, ProjectWithFile> projects = mapper.readValue(file, mapType);

            return projects.get(projectId).getFileMap();
        }catch (IOException e){
            e.getStackTrace();
        }
        return null;
    }

}
