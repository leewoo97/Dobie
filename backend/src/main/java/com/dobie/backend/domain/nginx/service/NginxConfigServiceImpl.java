package com.dobie.backend.domain.nginx.service;

import com.dobie.backend.domain.nginx.dto.NginxConfigGetResponseDto;
import com.dobie.backend.domain.nginx.dto.NginxProxyDto;

public class NginxConfigServiceImpl implements NginxConfigService{

    @Override
    public String createReverseProxyNginxConfig(NginxConfigGetResponseDto nginxConfig) {
        StringBuilder sb = new StringBuilder();
        sb.append("user nginx;\n");
        sb.append("worker_processes auto;\n");
        sb.append("\n");
        sb.append("error_log /var/log/nginx/error.log warn;\n");
        sb.append("pid /run/nginx.pid;\n");
        sb.append("\n");
        sb.append("events {\n");
        sb.append("    worker_connections 1024;\n");
        sb.append("}\n");
        sb.append("http {\n");
        sb.append("    include /etc/nginx/mime.types;\n");
        sb.append("    default_type application/octet-stream;\n");
        sb.append("    log_format main '$remote_addr - $remote_user [$time_local] \"$request\" '\n");
        sb.append("                    '$status $body_bytes_sent \"$http_referer\" '\n");
        sb.append("                    '\"$http_user_agent\" \"$http_x_forwarded_for\"'\n");
        sb.append("    access_log /var/log/nginx/access.log main;\n");
        sb.append("    sendfile on;\n");
        sb.append("    keepalive_timeout 65;\n");

        if(nginxConfig.isHttps()){
            sb.append(httpsConfig(nginxConfig));
        }else{
            sb.append(defaultConfig(nginxConfig));
        }




        return sb.toString();
    }
    public String httpsConfig(NginxConfigGetResponseDto nginxConfig){
        StringBuilder sb = new StringBuilder();
        sb.append("server {\n");
        sb.append("    listen 80;\n");
        sb.append("    server_name "+nginxConfig.getDomain()+"\n"); //도메인 없을경우 수정
        sb.append("    location / {\n");
        sb.append("        return 301 https://$host$request_url;\n");
        sb.append("   }\n");
        sb.append("}\n");
        sb.append("server {\n");
        sb.append("    listen 443 ssl\n");
        sb.append("    server_name "+nginxConfig.getDomain()+"\n"); //도메인 없을경우 수정
        sb.append("\n");
        sb.append("    ssl_certificate "+nginxConfig.getSslCertificate()+"\n");
        sb.append("    ssl_certificate_key "+nginxConfig.getSslCertificateKey()+"\n");
        //보안 강화 추가
        sb.append("\n");
        for(NginxProxyDto proxyConfig : nginxConfig.getLocations()){
            sb.append("    location ").append(proxyConfig.getLocation()).append(" {\n");
            sb.append("        proxy_pass ").append(proxyConfig.getProxyPass()).append(";\n");
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
    public String defaultConfig(NginxConfigGetResponseDto nginxConfig){
        StringBuilder sb = new StringBuilder();
        sb.append("server {\n");
        sb.append("    listen 80;\n");
        sb.append("    server_name "+nginxConfig.getDomain()+"\n");
        sb.append("    location / {\n");
        sb.append("        return 301 https://$host$request_url;\n");
        for(NginxProxyDto proxyConfig : nginxConfig.getLocations()){
            sb.append("    location ").append(proxyConfig.getLocation()).append(" {\n");
            sb.append("        proxy_pass ").append(proxyConfig.getProxyPass()).append(";\n");
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
