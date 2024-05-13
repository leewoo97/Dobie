package com.dobie.backend.util.command;

import com.dobie.backend.exception.exception.build.NginxRestartFailedException;
import com.dobie.backend.exception.exception.build.ServiceStopFailedException;
import com.dobie.backend.exception.exception.git.GitCheckoutFailedException;
import com.dobie.backend.exception.exception.git.GitCloneFailedException;
import com.dobie.backend.exception.exception.git.GitPullFailedException;
import com.dobie.backend.exception.exception.build.ProjectStartFailedException;
import com.dobie.backend.exception.exception.build.ProjectStopFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandServiceImpl implements CommandService {

    DefaultExecutor executor = new DefaultExecutor();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
    StringBuilder sb;

    // 이미 깃 클론이 되있는지 아닌지 체크하는 메서드
    // true이면 이미 clone
    // false 이면 clone 안됨
    @Override
    public boolean checkIsCloned(String path) {
        File directory = new File(path);
        if (directory.exists()) {
            System.out.println("directory exists");
            return true;
        } else {
            System.out.println("directory not exists");
            return false;
        }
    }

    // 깃 클론
    @Override
    public void gitClone(String repositoryURL, String accessToken) {
        StringBuilder sb = new StringBuilder();
        // URL에 액세스 토큰을 포함하여 인증 정보 제공
        String authUrl = repositoryURL.replace("https://", "https://oauth2:" + accessToken + "@");
        sb.append("git clone ").append(authUrl);
        CommandLine commandLine = CommandLine.parse(sb.toString());

        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);

        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("git clone success : " + result);
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new GitCloneFailedException(e.getMessage(), result);
        }
    }

    @Override
    public void gitCheckout(String path, String branchName) {
        sb = new StringBuilder();
        sb.append("git -C ").append(path).append(" checkout ").append(branchName);
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("git checkout success : " + result);
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new GitCheckoutFailedException(e.getMessage(), result);
        }
    }

    // git pull
    @Override
    public void gitPull(String path) {
        try {
            CommandLine commandLine1 = CommandLine.parse("pwd");
            executor.execute(commandLine1);

        } catch (Exception e) {
            e.getStackTrace();
        }

        sb = new StringBuilder();
        sb.append("git -C ").append(path).append(" pull");
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("git pull success : " + result);
        } catch (Exception e) {
            System.out.println(outputStream.toString().trim());
            throw new GitPullFailedException(e.getMessage(), e);
        }
    }
//    @Override
//    public void gitPull(String path, String branchName) {
//        sb = new StringBuilder();
//        sb.append("git -C ").append(path).append(" pull origin ").append(branchName);
//        CommandLine commandLine = CommandLine.parse(sb.toString());
//        executor.setStreamHandler(streamHandler);
//        try {
//            executor.execute(commandLine);
//            String result = outputStream.toString().trim();
//            System.out.println("git pull success : " + result);
//        } catch (Exception e) {
//            throw new GitPullFailedException(e.getMessage());
//        }
//    }


    // 빌드
    @Override
    public void build(String path, String projectName) {
        sb = new StringBuilder();
        sb.append("docker build ").append(projectName + " .");

        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.setWorkingDirectory(new File(path));
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("빌드 성공: " + result);
        } catch (Exception e) {
            System.out.println("빌드 중 에러 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run(String path, int port1, int port2) {
        sb = new StringBuilder();
        sb.append("docker run -p ").append(port1).append(":").append(port2).append(" welcome");

        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.setWorkingDirectory(new File(path));
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("실행 성공: " + result);
        } catch (Exception e) {
            System.out.println("실행 중 에러 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void dockerComposeUp(String path) {
        sb = new StringBuilder();
        sb.append("docker compose -f ").append(path+"/docker-compose.yml").append(" up --build -d");

        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("compose up success : " + result);
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new ProjectStartFailedException(e.getMessage(), result);
        }
    }

    @Override
    public void dockerComposeDown(String path) {
        sb = new StringBuilder();
        sb.append("docker compose -f ").append(path+"/docker-compose.yml").append(" down --rmi all");
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("compose down success : " + result);
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new ProjectStopFailedException(e.getMessage(), result);
        }
    }

    @Override
    public void dockerStop(String containerName) {
        sb = new StringBuilder();
        sb.append("docker stop ").append(containerName);
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("docker stop success : " + result);
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new ServiceStopFailedException(e.getMessage(), result);
        }
    }

    @Override
    public void dockerStart(String containerName) {
        sb = new StringBuilder();
        sb.append("docker start ").append(containerName);
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("docker start success : " + result);
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new ServiceStopFailedException(e.getMessage(), result);
        }
    }

    @Override
    public void restartNginx() {
        sb = new StringBuilder();
        sb.append("docker restart nginx");
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("restartNginx success : " + result);
        }catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new NginxRestartFailedException(e.getMessage(), result);
        }
    }
}

