package com.dobie.backend.domain.docker.dockerfile.service;

import com.dobie.backend.domain.docker.readjson.service.ReadJsonService;
import com.dobie.backend.exception.exception.docker.DockerPsErrorException;
import com.dobie.backend.exception.exception.Environment.*;
import com.dobie.backend.exception.exception.build.BackendBuildFailedException;
import com.dobie.backend.exception.exception.build.FrontendBuildFailedException;
import com.dobie.backend.exception.exception.docker.DockerPsLinePartsErrorException;
import com.dobie.backend.exception.exception.file.SaveFileFailedException;
import com.dobie.backend.util.file.FileManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class DockerfileServiceImpl implements DockerfileService {

    private final ReadJsonService readJsonService;

    FileManager fileManager = new FileManager();

    @Override
    public void createGradleDockerfile(String projectName, String version, String path) {

        StringBuilder sb = new StringBuilder();
        sb.append("FROM openjdk:").append(version).append("-slim\n");
        sb.append("RUN apt-get update && apt-get install -y docker.io\n");
        sb.append("VOLUME /var/run/docker.sock\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY . /app\n");
        sb.append("RUN chmod +x ./gradlew\n");
        sb.append("RUN ./gradlew clean bootJar -x test\n");
        sb.append("RUN cp $(ls -t build/libs/*.jar | head -n 1) app.jar\n");
        sb.append("ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\n");

//        sb.append("RUN --mount=type=cache,target=/root/.gradle ./gradlew clean build\n");
//        sb.append("ARG JAR_FILE=build/libs/*.jar\n");
//        sb.append("COPY ${JAR_FILE} app.jar\n");
//        sb.append("ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\n");
        String dockerfile = sb.toString();

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectName + path;
        // 경로에 build.Gradle이 존재X 또는 경로 자체가 잘못되었다면 오류 발생
        checkBuildGradle(filePath);
        try {
            fileManager.saveFile(filePath, "Dockerfile", dockerfile);
        } catch (SaveFileFailedException e) {
            throw new BackendBuildFailedException(e.getErrorMessage());
        }


    }

    @Override
    public void createMavenDockerfile(String projectName, String version, String path) {

        StringBuilder sb = new StringBuilder();
        sb.append("FROM openjdk:").append(version).append("-slim\n");
        sb.append("RUN apt-get update && apt-get install -y maven\n");
        sb.append("VOLUME /var/run/docker.sock\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY . /app\n");
        sb.append("RUN mvn clean package -DskipTests\n");
        sb.append("RUN cp target/*.jar app.jar\n");
        sb.append("ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\n");
        String dockerfile = sb.toString();

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectName + path;
        checkBuildPom(filePath);
        try {
            fileManager.saveFile(filePath, "Dockerfile", dockerfile);
        } catch (SaveFileFailedException e) {
            throw new BackendBuildFailedException(e.getErrorMessage());
        }

    }

    @Override
    public void createReactDockerfile(String projectName, String version, String path) {

        StringBuilder sb = new StringBuilder();
        sb.append("FROM node:").append(version).append("-alpine as build-stage\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY package*.json ./\n");
        sb.append("RUN npm install\n");
        sb.append("COPY . .\n");
        sb.append("RUN npm run build\n");
        sb.append("CMD [ \"npm\", \"start\" ]\n");
        String dockerfile = sb.toString();

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectName + path;
        checkBuildPackageJson(filePath);
        try {
            fileManager.saveFile(filePath, "Dockerfile", dockerfile);
        } catch (SaveFileFailedException e) {
            throw new FrontendBuildFailedException(e.getErrorMessage());
        }
    }

    @Override
    public void createVueDockerfile(String projectName, String version, String path) {

        StringBuilder sb = new StringBuilder();
        sb.append("FROM node:").append(version).append("-alpine as build-stage\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY package*.json ./\n");
        sb.append("RUN npm install\n");
        sb.append("COPY . .\n");
        sb.append("RUN npm run build\n");
        sb.append("FROM node:20.11.0-alpine\n");
        sb.append("WORKDIR /app\n");
        sb.append("COPY --from=build-stage /app/dist /app\n");
        sb.append("CMD [\"npx\", \"http-server\", \"-p\", \"5173\"]\n");
        String dockerfile = sb.toString();

        // ec2 서버에서 깃클론하는 경로로 수정하기
        String filePath = "./" + projectName + path;
        checkBuildPackageJson(filePath);
        try {
            fileManager.saveFile(filePath, "Dockerfile", dockerfile);
        } catch (SaveFileFailedException e) {
            throw new FrontendBuildFailedException(e.getErrorMessage());
        }
    }

    @Override
    public void checkBuildGradle(String filepath) {
        File directory = new File(filepath); // 디렉토리 경로 지정
        File[] filesList = directory.listFiles(); // 디렉토리의 모든 파일 및 폴더 목록 얻기
        boolean correctPath = false;
        if (filesList != null) {
            for (File file : filesList) {
                if (file.getName().equals("build.gradle")) {
//                    System.out.println("Name: " + file.getName()); // 파일 또는 디렉토리 이름 출력
                    correctPath = true;
                    break;
                }
            }
            if (!correctPath) {
//                System.out.println("파일 경로에 bulid.gradle이 존재하지않습니다.");
                throw new BuildGradleNotFoundException();
            }
        } else {
//            System.out.println("파일 경로 자체가 잘못되었음.");
            throw new BackendFilePathNotExistException();
        }
    }

    @Override
    public void checkBuildPom(String filepath) {
        File directory = new File(filepath); // 디렉토리 경로 지정
        File[] filesList = directory.listFiles(); // 디렉토리의 모든 파일 및 폴더 목록 얻기
        boolean correctPath = false;
        if (filesList != null) {
            for (File file : filesList) {
                if (file.getName().equals("pom.xml")) {
//                    System.out.println("Name: " + file.getName()); // 파일 또는 디렉토리 이름 출력
                    correctPath = true;
                    break;
                }
            }
            if (!correctPath) {
//                System.out.println("파일 경로에 pom.xml이 존재하지않습니다.");
                throw new PomXmlNotFoundException();
            }
        } else {
//            System.out.println("파일 경로 자체가 잘못되었음.");
            throw new BackendFilePathNotExistException();
        }
    }

    @Override
    public void checkBuildPackageJson(String filepath) {
        File directory = new File(filepath); // 디렉토리 경로 지정
        File[] filesList = directory.listFiles(); // 디렉토리의 모든 파일 및 폴더 목록 얻기
        boolean correctPath = false;
        if (filesList != null) {
            for (File file : filesList) {
                if (file.getName().equals("package.json")) {
//                    System.out.println("Name: " + file.getName()); // 파일 또는 디렉토리 이름 출력
                    correctPath = true;
                    break;
                }
            }
            if (!correctPath) {
//                System.out.println("파일 경로에 pom.xml이 존재하지않습니다.");
                throw new PackageJsonNotFoundException();
            }
        } else {
//            System.out.println("파일 경로 자체가 잘못되었음.");
            throw new FrontendFilePathNotExistException();
        }
    }

    @Override
    public HashMap<String, String> dockerContainerLister() {
        CommandLine commandLine = new CommandLine("docker");
        commandLine.addArgument("ps");
        commandLine.addArgument("-a"); // "-a" 옵션 추가

        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);

        try {
            executor.execute(commandLine);
            String dockerOutput = outputStream.toString();
//            System.out.println("docker ps -a결과: \n" + dockerOutput);
            HashMap<String,String> containers = parseDockerPsOutput(dockerOutput);
//            for (String key : containers.keySet()) {
//                System.out.println("Key: " + key + ", Value: " + containers.get(key));
//            }
            return containers;

        } catch (Exception e) {
//            System.out.println("docker ps 명령어 실행 중 에러 발생: " + e.getMessage());
//            e.printStackTrace();
            throw new DockerPsErrorException();
        }
    }


    @Override
    public String makeDockerfilePathContent(String projectId, String serviceId, String type) {
        if(type.equals("Backend")) {
            try {
            //project.json을 불러오는 메소드 -> readJsonService.JsonToMap()
            //data/projectJson을 map으로 변환해서 불러왔음
            Map<String, Object> projectJsonMap = readJsonService.JsonToMap();
//        System.out.println("맵으로 표현한 projectJsonMap => " + projectJsonMap);
            String projectName = (String) readJsonService.JsonGetTwo(projectJsonMap, projectId, "projectName");
//        System.out.println("프로젝트 이름 나오겠지? " + projectName);
            String path = (String) readJsonService.JsonGetFour(projectJsonMap, projectId, "backendMap", serviceId, "path");
//        System.out.println("백엔드 경로 나오겠지? " + path);
            String filepath = "/" + projectName + path;
//            System.out.println("파일 경로 : " + filepath);
//            return new String(Files.readAllBytes(Paths.get(filePath)));
                return filepath;
            }catch (Exception e) {
                System.out.println("File not found or error reading file: " + e.getMessage());
                throw new makeDockerfilePathContentException();
//            return "File not found or error reading file: " + e.getMessage();
            }
        }else if(type.equals("Frontend")){
            try {
            //project.json을 불러오는 메소드 -> readJsonService.JsonToMap()
            //data/projectJson을 map으로 변환해서 불러왔음
            Map<String, Object> projectJsonMap = readJsonService.JsonToMap();
//        System.out.println("맵으로 표현한 projectJsonMap => " + projectJsonMap);
            String projectName = (String) readJsonService.JsonGetTwo(projectJsonMap, projectId, "projectName");
//        System.out.println("프로젝트 이름 나오겠지? " + projectName);
            String path = (String) readJsonService.JsonGetThree(projectJsonMap, projectId, "frontend","path");
//        System.out.println("백엔드 경로 나오겠지? " + path);
            String filepath = "/" + projectName + path;
//            System.out.println("파일 경로 : " + filepath);
//            return new String(Files.readAllBytes(Paths.get(filePath)));
                return filepath;
            }catch (Exception e) {
                System.out.println("File not found or error reading file: " + e.getMessage());
                throw new makeDockerfilePathContentException();
//            return "File not found or error reading file: " + e.getMessage();
            }
        }else{
            throw new TypeErrorException();
        }
    }

    @Override
    public String readEnvironmentDockerFile(String filepath) {

        CommandLine commandLine = new CommandLine("docker");
        commandLine.addArgument("exec");
//        commandLine.addArgument("dobie-be"); //dobie-be 컨테이너에 접속하는건 고정(만약 나중에 명칭 바뀌면 바꿔줘야함)
        commandLine.addArgument("5fade2f00236"); //dobie-be 컨테이너에 접속하는건 고정(만약 나중에 명칭 바뀌면 바꿔줘야함)
        commandLine.addArgument("cat");
        commandLine.addArgument(filepath+"/Dockerfile"); //이렇게하면 아마 될걸..? 컨테이너에 ko2sist도비 말고 다른 컨테이너도 빌드해야 테스트 가능 ㅠ

        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);

        try {
            executor.execute(commandLine);
            String fileContent = outputStream.toString();
//            System.out.println("File content: \n" + fileContent);
            return fileContent;
        } catch (Exception e) {
//            System.err.println("Error during file reading: " + e.getMessage());
            throw new DockerFileContentNotFoundException();
        }
    }


//----------------------------------------------------------------------------------------------------

    public HashMap<String,String> parseDockerPsOutput(String output) {
        HashMap<String,String> containers = new HashMap<>();
        String[] lines = output.split("\n");
        try {
            // 첫 번째 줄은 헤더이므로 건너뜀
            for (int i = 1; i < lines.length; i++) {
                String[] parts = lines[i].split("\\s{2,}");  // 두 개 이상의 공백으로 분할
                if (parts.length == 7) {
                    String containerId = parts[0];
                    String image = parts[1];
                    String command = parts[2];
                    String created = parts[3];
                    String status = parts[4];
                    String currentStatus = checkStatus(status);
                    String ports = parts[5];
                    String innerPort = splitPorts(ports, "inner");
                    String outerPort = splitPorts(ports, "outer");
                    String names = parts[6];
//                String frameWork = splitName(names);
                    containers.put(names, currentStatus);
                } else {
                    String containerId = parts[0];
                    String image = parts[1];
                    String command = parts[2];
                    String created = parts[3];
                    String status = parts[4];
                    String currentStatus = checkStatus(status);
                    String names = parts[5];
//                String frameWork = splitName(names);
                    containers.put(names, currentStatus);
                }

            }
        }catch(Exception e){
            throw new DockerPsLinePartsErrorException();
        }


        return containers;
    }


    //    public String splitName(String names){
//        String[] temp = name.split("-");
//        return temp[temp.length-2];
//    }
    public String checkStatus(String status){
        if(status.contains("Up")){
            return "Running :)";
        }else if(status.contains("Exited")){
            return "Stopped :(";
        }else if(status.contains("Created")){
            return "Created :|";
        }else{
            throw new CurrentStatusNotFoundException();
        }
    }
    public String splitPorts(String ports,String type){
        if(type.equals("inner")) {
            // ':'을 기준으로 문자열을 나눕니다.
            String[] parts = ports.split(":");

            // 나누어진 두 번째 부분('-'를 포함)에서 다시 '-'를 기준으로 나눕니다.
            String[] subParts = parts[1].split("->");

            // 결과적으로 첫 번째 부분이 포트 번호가 됩니다.
            return subParts[0];
        }
        else if(type.equals("outer")){
            // ':'을 기준으로 문자열을 나눕니다.
            String[] parts = ports.split("->");

            // 나누어진 두 번째 부분('-'를 포함)에서 다시 '-'를 기준으로 나눕니다.
            String[] subParts = parts[1].split("/");

            // 결과적으로 첫 번째 부분이 포트 번호가 됩니다.
            return subParts[0];
        }
        else{
            throw new PortNumberNotFoundException();
        }
    }

}
