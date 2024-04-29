package com.dobie.backend.domain.nginx.service;

import com.dobie.backend.domain.nginx.dto.NginxConfigGetResponseDto;
import com.dobie.backend.domain.nginx.dto.NginxProxyDto;

public class NginxConfigServiceImpl implements NginxConfigService{

    @Override
    public String createReverseProxyNginxConfig(NginxConfigGetResponseDto nginxConfig) {
        StringBuilder sb = new StringBuilder();

        if(nginxConfig.isHttps()){
            sb.append(httpsConfig(nginxConfig));
        }else{
            sb.append(defaultConfig(nginxConfig));
        }

        return sb.toString();
    }
    @Override
    public String httpsConfig(NginxConfigGetResponseDto nginxConfig){
        StringBuilder sb = new StringBuilder();
        sb.append("server {\n");
        sb.append("    listen 80;\n"); //80번 포트로 요청이 들어왔을때
        sb.append("    server_name "+nginxConfig.getDomain()+"\n");
        sb.append("    location / {\n");
        sb.append("        return 301 https://$host$request_url;\n"); //443포트로 보내기(https적용할 수 있도록)
        sb.append("   }\n");
        sb.append("}\n");
        sb.append("server {\n");
        sb.append("    listen 443 ssl\n"); //443포트로 요청이 들어왔을때 (ssl은 https의 보안기능을 사용한다~~ 이런 느낌)
        sb.append("    server_name "+nginxConfig.getDomain()+"\n"); //사용할 서버네입
        sb.append("\n");
        sb.append("    ssl_certificate "+nginxConfig.getSslCertificate()+"\n"); //직접 받은 ssl certificate 넣기(우리가 받아주는 방법 찾아보기)
        sb.append("    ssl_certificate_key "+nginxConfig.getSslCertificateKey()+"\n"); //직접 받은 ssl certificate key 넣기
        //보안 강화 추가
        sb.append("\n");
        //locations 리스트 for문 통해서 config 작성
        for(NginxProxyDto proxyConfig : nginxConfig.getLocations()){
            sb.append("    location ").append(proxyConfig.getLocation()).append(" {\n"); //location 경로 (ex. location /api {)
            sb.append("        proxy_pass ").append(proxyConfig.getServiceName()).append(":").append(proxyConfig.getPort()).append(";\n"); //위의 경로로 요청이 왔을때 연결할 경로(ex. https://www.catale.8080)
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
            sb.append("}\n");
        }

        return sb.toString();
    }
    @Override
    public String defaultConfig(NginxConfigGetResponseDto nginxConfig){
        StringBuilder sb = new StringBuilder();
        sb.append("server {\n");
        sb.append("    listen 80;\n");
        sb.append("    server_name "+nginxConfig.getDomain()+"\n");
        sb.append("    location / {\n");
        sb.append("        return 301 https://$host$request_url;\n");
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
            sb.append("}\n");
        }
        sb.append("   }\n");
        sb.append("}\n");
        return "";
    }
}
