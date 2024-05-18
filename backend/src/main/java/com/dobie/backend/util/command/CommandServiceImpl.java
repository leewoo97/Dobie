package com.dobie.backend.util.command;

import com.dobie.backend.exception.exception.build.*;
import com.dobie.backend.exception.exception.file.DeleteFileFailedException;
import com.dobie.backend.exception.exception.git.GitCheckoutFailedException;
import com.dobie.backend.exception.exception.git.GitCloneFailedException;
import com.dobie.backend.exception.exception.git.GitPullFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.*;

import static com.dobie.backend.exception.format.response.ErrorCode.SSL_CERTIFICATE_ISSUE_FAILED;

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
        sb.append("docker compose -f ").append(path + "/docker-compose.yml").append(" up --build -d");

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
        sb.append("docker compose -f ").append(path + "/docker-compose.yml").append(" down --rmi all");
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
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new NginxRestartFailedException(e.getMessage(), result);
        }
    }

    @Override
    public void stopNginx() {
        sb = new StringBuilder();
        sb.append("docker stop nginx");
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("stopNginx success : " + result);
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new NginxStopFailedException(e.getMessage(), result);
        }
    }

    @Override
    public void deleteNginxProxyConf(String projectId) {
        sb = new StringBuilder();
        sb.append("rm -f /nginx/").append(projectId).append(".conf");
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("deleteNginxConf success : " + result);
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new NginxConfDeleteFailedException(e.getMessage(), result);
        }
    }

    @Override
    public void deleteFile(String fileName, String path){
        sb = new StringBuilder();
        sb.append("rm -f ").append(path).append(fileName);
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("delete "+fileName+" success : " + result);
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new DeleteFileFailedException(e.getMessage(), result);
        }
    }

    @Override
    public void deleteDirectory(String directoryPath) {
        // 디렉토리 객체 생성
        File directory = new File(directoryPath);

        // 디렉토리 존재 여부 확인
        if (directory.exists()) {
            StringBuilder sb = new StringBuilder();
            // Linux/Unix 기반 시스템의 경우
            sb.append("rm -rf ").append(directory.getAbsolutePath());
            // Windows 기반 시스템의 경우 아래의 명령어를 사용
            // sb.append("cmd /c rd /s /q ").append(directory.getAbsolutePath());

            CommandLine commandLine = CommandLine.parse(sb.toString());

            DefaultExecutor executor = new DefaultExecutor();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            executor.setStreamHandler(streamHandler);

            try {
                executor.execute(commandLine);
                String result = outputStream.toString().trim(); // 명령어 실행 결과를 문자열로 받음
                System.out.println("Directory delete success: " + result);
            } catch (Exception e) {
                String result = outputStream.toString().trim();
                throw new RuntimeException("Directory Delete Failed: " + e.getMessage() + ", " + result);
            }
        } else {
            throw new RuntimeException("Directory not found: " + directoryPath);
        }
    }

    @Override
    public void getSSLTest(String domain) throws IOException {
        // 호스트의 파이프 경로
        String pipePath = "/getSSL_pipe";
            // 호스트의 파이프에 쓰기 위한 BufferedWriter 생성
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pipePath)));

            // 전달할 명령어
            String command = "sudo certbot certonly --standalone --email test@test.com --agree-tos --no-eff-email --keep-until-expiring -d "+domain;

            // 명령어를 파이프에 씀
            writer.write(command);
            writer.newLine();
            writer.flush(); // 버퍼 비우기
            writer.close(); // 파일 닫기

            System.out.println("명령어를 성공적으로 파이프에 전달했습니다.");

        sb = new StringBuilder();
        sb.append("cat /logfile.log");
        CommandLine commandLine = CommandLine.parse(sb.toString());
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(commandLine);
            String result = outputStream.toString().trim();
            System.out.println("ssl issued log : " + result);

            if(result.contains("no action taken")){
                log.info("인증서가 아직 유효합니다.");
                System.out.println("인증서가 아직 유효합니다.");
            }else if(result.contains("Congratulations")) {
                log.info("인증서가 성공적으로 발급되었습니다.");
                System.out.println("인증서가 성공적으로 발급되었습니다.");
            }else {
                log.info("인증서 발급실패");
                System.out.println("Error : 인증서 발급실패");
                throw new SSLCertificateIssueFailedException();
            }
        } catch (Exception e) {
            String result = outputStream.toString().trim();
            throw new DeleteFileFailedException(e.getMessage(), result);
        }
    }

}

