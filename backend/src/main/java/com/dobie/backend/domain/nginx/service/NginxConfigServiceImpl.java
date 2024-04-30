package com.dobie.backend.domain.nginx.service;

import com.dobie.backend.domain.nginx.dto.NginxConfigGetResponseDto;
import com.dobie.backend.domain.nginx.dto.NginxProxyDto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NginxConfigServiceImpl implements NginxConfigService{

    @Override
    public String createReverseProxyNginxConfig(NginxConfigGetResponseDto nginxConfig) {
        StringBuilder sb = new StringBuilder();

        if(nginxConfig.isHttps()){
            sb.append(withHttpsConfig(nginxConfig));
        }else{
            sb.append(withoutHttpsConfig(nginxConfig));
        }

        return sb.toString();
    }

    @Override
    public void saveConfigFile(String path, String projectName) throws IOException {
        String savePath = "/" + projectName + path + "/conf/conf.d";
        if (!new File(savePath).exists()) {
            new File(savePath).mkdirs();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter((savePath+"/default.conf")));
        writer.write(createFrontNginxConfig());
        writer.close();
    }

    @Override
    public String withHttpsConfig(NginxConfigGetResponseDto nginxConfig){
        StringBuilder sb = new StringBuilder();
        // HTTP를 HTTPS로 리디렉션하는 서버 블록
        sb.append("server {\n");
        sb.append("    listen 80;\n"); //80번 포트로 요청이 들어왔을때
        sb.append("    server_name "+nginxConfig.getDomain()+";\n");
        sb.append("    location / {\n");
        sb.append("        return 301 https://$host$request_uri;\n"); //443포트로 보내기(https적용할 수 있도록)
        sb.append("    }\n");
        sb.append("}\n");
        sb.append("\n");

        // HTTPS를 처리하는 서버 블록
        sb.append("server {\n");
        sb.append("    listen 443 ssl;\n"); // 443번 포트로 HTTPS 요청이 들어왔을 때, SSL을 사용한다는 의미
        sb.append("    server_name "+nginxConfig.getDomain()+";\n"); // 사용할 도메인 설정
        sb.append("\n");
        sb.append("    ssl_certificate "+nginxConfig.getSslCertificate()+"\n"); // SSL 인증서 설정
        sb.append("    ssl_certificate_key "+nginxConfig.getSslCertificateKey()+"\n"); // SSL 인증서 키 설정
        //보안 강화 추가
        sb.append("\n");

        //locations 리스트 for문 통해서 config 작성
        for(NginxProxyDto proxyConfig : nginxConfig.getLocations()){
            sb.append("    location ").append(proxyConfig.getLocation()).append(" {\n"); //location 경로설정 (ex. location /api {)
            sb.append("        proxy_pass ").append(proxyConfig.getServiceName()).append(":").append(proxyConfig.getPort()).append(";\n"); //위의 경로로 요청이 왔을때 연결할 경로(ex. https://www.catale.8080)
            sb.append("        proxy_http_version 1.1;\n"); // HTTP 프로토콜 버전 설정
            sb.append("        proxy_set_header Connection \"\";\n"); // Connection 헤더 설정
            sb.append("        proxy_redirect off;\n"); // 리다이렉션 설정 비활성화
            sb.append("\n");
            //헤더에 클라이언트의 요청을 실제 서버로 전달할 때 관련된 설정
            sb.append("        proxy_set_header Host $host;\n"); //헤어데 클라이언트가 요청한 호스트 유지
            sb.append("        proxy_set_header X-Real-IP $remote_addr;\n"); //헤더에 클라이언트 실제 ip주소 저장
            sb.append("        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;\n"); //클라이언트가 사용한 프로토콜 유지
            sb.append("        proxy_set_header X-Forwarded-Proto $scheme;\n");
            sb.append("        proxy_set_header X-Forwarded-Host $server_name;\n");
            sb.append("        proxy_set_header X-Forwarded-Port $server_port;\n");
            sb.append("\n");
            sb.append("        proxy_read_timeout 300;\n");// 프록시 읽기 시간 제한 설정
            sb.append("    }\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
    @Override
    public String withoutHttpsConfig(NginxConfigGetResponseDto nginxConfig){
        StringBuilder sb = new StringBuilder();
        sb.append("server {\n");
        sb.append("    listen 80;\n");
        sb.append("    server_name "+nginxConfig.getDomain()+";\n");
        sb.append("\n");
        for(NginxProxyDto proxyConfig : nginxConfig.getLocations()){
            sb.append("    location ").append(proxyConfig.getLocation()).append(" {\n");
            sb.append("        proxy_pass ").append(proxyConfig.getServiceName()).append(":").append(proxyConfig.getPort()).append(";\n");
            sb.append("        proxy_http_version 1.1;\n");
            sb.append("        proxy_set_header Connection \"\";\n");
            sb.append("        proxy_redirect off;\n");
            sb.append("\n");
            sb.append("        proxy_set_header Host $host;\n");
            sb.append("        proxy_set_header X-Real-IP $remote_addr;\n");
            sb.append("        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;\n");
            sb.append("        proxy_set_header X-Forwarded-Proto $scheme;\n");
            sb.append("        proxy_set_header X-Forwarded-Host $server_name;\n");
            sb.append("        proxy_set_header X-Forwarded-Port $server_port;\n");
            sb.append("\n");
            sb.append("        proxy_read_timeout 300;\n");
            sb.append("    }\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    @Override
    public String createFrontNginxConfig(){
        StringBuilder sb = new StringBuilder();
        sb.append("server {\n");
        sb.append("    listen 80;\n");
        sb.append("    client_max_body_size 10M;\n");
        sb.append("    location / {\n")
                .append("        root /usr/share/nginx/html;\n")
                .append("        index index.html index.htm;\n")
                .append("        try_files $uri $uri/ /index.html;\n")
                .append("    }\n");
        sb.append("\n");
        sb.append("    error_page   500 502 503 504  /50x.html;\n");
        sb.append("\n");
        sb.append("    location = /50x.html {\n")
                .append("        root /usr/share/nginx/html;\n")
                .append("    }\n");
        sb.append("}\n");
        return sb.toString();

    }
}
