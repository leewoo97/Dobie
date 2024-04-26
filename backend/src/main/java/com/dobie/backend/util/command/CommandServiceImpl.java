package com.dobie.backend.util.command;

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
            System.out.println("git clone 성공: " + result);
        } catch (Exception e) {
            System.out.println("glt clone 명령어 실행 중 에러 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void gitCloneGitLab(String repositoryURL, String username, String password) {
        StringBuilder sb = new StringBuilder();
        // URL에 사용자 이름과 액세스 토큰을 포함
        String authUrl = repositoryURL.replace("https://", "https://" + username + ":" + password + "@");
        sb.append("git clone ").append(authUrl);
        CommandLine commandLine = CommandLine.parse(sb.toString());

        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);

        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("git clone 성공: " + result);
        } catch (Exception e) {
            System.out.println("git clone 명령어 실행 중 에러 발생: " + e.getMessage());
            e.printStackTrace();
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
            System.out.println("git checkout 성공: " + result);
        } catch (Exception e) {
            System.out.println("git checkout 명령어 실행 중 에러 발생: " + e.getMessage());
            e.printStackTrace();
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
            System.out.println("git pull 성공: " + result);
        } catch (Exception e) {
            System.out.println("git pull 명령어 실행 중 에러 발생: " + e.getMessage());
            e.printStackTrace();
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
    public void dockerComposeDown() {
        sb = new StringBuilder();
        sb.append("docker compose down");
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
            System.out.println("compose down 성공: " + result);
        } catch (Exception e) {
            System.out.println("compose down 중 에러 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


