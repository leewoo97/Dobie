package com.dobie.backend.util.command;

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
    public void gitClone(String repositoryURL) {
        sb = new StringBuilder();
        sb.append("git clone ").append(repositoryURL);
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("git clone success : " + result);
        } catch (Exception e) {
            GitCloneFailedException gitCloneFailedException = new GitCloneFailedException();
            System.out.println(gitCloneFailedException.getMessage());
            System.out.println("Error : " + e.getMessage());
            throw gitCloneFailedException;
        }
    }

    @Override
    public void gitCloneGitLab(String repositoryURL, String accessToken) {
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
            GitCloneFailedException gitCloneFailedException = new GitCloneFailedException();
            System.out.println(gitCloneFailedException.getMessage());
            System.out.println("Error : " + e.getMessage());
            throw gitCloneFailedException;
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
            GitCheckoutFailedException gitCheckoutFailedException = new GitCheckoutFailedException();
            System.out.println(gitCheckoutFailedException.getMessage());
            System.out.println("Error : " + e.getMessage());
            throw gitCheckoutFailedException;
        }
    }

    // git pull
    @Override
    public void gitPull(String path, String branchName) {
        sb = new StringBuilder();
        sb.append("git -C ").append(path).append(" pull origin ").append(branchName);
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("git pull success : " + result);
        } catch (Exception e) {
            GitPullFailedException gitPullFailedException = new GitPullFailedException();
            System.out.println(gitPullFailedException.getMessage());
            System.out.println("Error message: " + e.getMessage());
            throw gitPullFailedException;
        }
    }


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
        sb.append("docker compose up --build ");

        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.setWorkingDirectory(new File(path));
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("compose up success : " + result);
        } catch (Exception e) {
            ProjectStartFailedException projectStartFailedException = new ProjectStartFailedException();
            System.out.println(projectStartFailedException.getMessage());
            System.out.println("Error : " + e.getMessage());
            throw projectStartFailedException;
        }
    }

    @Override
    public void dockerComposeDown(String path) {
        sb = new StringBuilder();
        sb.append("docker compose down");
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.setWorkingDirectory(new File(path));
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("compose down success : " + result);
        } catch (Exception e) {
            ProjectStopFailedException projectStopFailedException = new ProjectStopFailedException();
            System.out.println(projectStopFailedException.getMessage());
            System.out.println("Error : " + e.getMessage());
            throw projectStopFailedException;
        }
    }
}

