package com.dobie.backend.domain.nginx.controller;

import com.dobie.backend.domain.nginx.service.NginxConfigService;
import com.dobie.backend.exception.format.code.ApiResponse;
import com.dobie.backend.exception.format.response.ErrorCode;
import com.dobie.backend.exception.format.response.ResponseCode;
import com.dobie.backend.util.command.CommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
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
    private final CommandService commandService;

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

    @Operation(summary = "ssl", description = "ssl")
    @GetMapping ("/ssltest")
    public ResponseEntity<?> getSSLCertificateTest(@RequestParam(name = "domain") String domain){
       try {
           commandService.getSSLTest(domain);
       }catch (IOException e) {
           return response.error(ErrorCode.GET_SSL_FAILED);
       }
        return response.success(ResponseCode.NGINX_CONFIG_READ_SUCCESS);
    }
}
