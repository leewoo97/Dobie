package com.dobie.backend.domain.nginx.controller;

import com.dobie.backend.domain.nginx.service.NginxConfigService;
import com.dobie.backend.exception.format.code.ApiResponse;
import com.dobie.backend.exception.format.response.ErrorCode;
import com.dobie.backend.exception.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

@Tag(name = "Nginx 컨트롤러", description = "Nginx Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nginx")
public class NginxController {

    private final ApiResponse response;
    private final NginxConfigService nginxConfigService;

    @Operation(summary = "nginx config 파일 조회", description = "nginx config 파일 내용 조회")
    @GetMapping ("")
    public ResponseEntity<?> getNginxConfig(@RequestParam(name = "projectId") String projectId){
        String nginxConfFile = "";
        try {
            String nginxConfPath = "/nginx/"+projectId+".conf";
            nginxConfFile = nginxConfigService.readNginxFile(nginxConfPath);
        }catch(IOException e) {
            return response.error(ErrorCode.NGINX_CONFIG_READ_FAILED);
        }

        return response.success(ResponseCode.NGINX_CONFIG_READ_SUCCESS, nginxConfFile);
    }

    @Operation(summary = "ssl 인증서 발급", description = "https를 위한 ssl 인증서 발급")
    @GetMapping ("/ssl")
    public ResponseEntity<?> getSsl(@RequestParam(name = "projectDomain") String projectDomain){
        String pythonScriptPath = "/sslConf/get_certificate.py"; // 파이썬 스크립트의 경로

        // 사용자로부터 도메인 입력 받기
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter domain:");
        String domain = null;
        try {
            domain = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 파이썬 스크립트를 실행하면서 도메인 전달
        if (domain != null && !domain.isEmpty()) {
            try {
                Process process = Runtime.getRuntime().exec("python " + pythonScriptPath + " " + projectDomain);

                // 실행 결과 출력
                BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = outputReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Domain is empty or null.");
        }

        return response.success(ResponseCode.NGINX_CONFIG_READ_SUCCESS);
    }
}
