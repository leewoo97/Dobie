package com.dobie.backend.domain.nginx.service;

import com.dobie.backend.domain.project.dto.NginxConfigDto;
import com.dobie.backend.domain.project.dto.NginxProxyDto;
import com.dobie.backend.domain.project.service.ProjectService;
import com.dobie.backend.util.file.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Log4j2
public class NginxConfigServiceImpl implements NginxConfigService{

    private final ProjectService projectService;
    FileManager fileManager = new FileManager();

    //리버스프록시 nginx config 파일 생성 후 /nginx에 [projectName].conf 이름으로 저장
    @Override
    public void saveProxyNginxConfig(int projectId, String projectName) {
        NginxConfigDto nginxConfig = projectService.getNginxConfigDto(projectId); //projectId로 nginxConfigDto 찾아오기
        StringBuilder sb = new StringBuilder(); //config내용 저장할 StringBuilder

        //https사용 유무 확인
        if(nginxConfig.isUsingHttps()){
            sb.append(withHttpsConfig(nginxConfig)); //https 사용시 config파일 생성
        }else{
            sb.append(withoutHttpsConfig(nginxConfig)); //https 미사용시 config파일 생성
        }

        String fileName = projectName + ".conf"; //파일이름 [projectName].conf로 만들어주기
        fileManager.saveFile("/nginx",fileName,sb.toString()); //fileManager활용해서 /nginx경로에 저장하기
    }

    //프론트 nginx config 파일 생성 후 /[projectName]/[frontendPath]/conf/conf.d 파일에 default.conf이름으로 저장
    @Override
    public void saveFrontNginxConfigFile(String path, String projectName) throws IOException {
        String savePath = "/" + projectName + path + "/conf/conf.d"; //파일 저장할 경로 생성
        //해당 파일 경로 이미 있는지 확인
        if (!new File(savePath).exists()) {
            new File(savePath).mkdirs(); //없으면 새로 만들기
        }
        //경로에 default.conf이름으로 파일 저장
        BufferedWriter writer = new BufferedWriter(new FileWriter((savePath+"/default.conf")));
        writer.write(createFrontNginxConfig()); //default.conf파일에 config 내용 저장
        writer.close();
    }

    //https 사용하는 nginx config 생성
    @Override
    public String withHttpsConfig(NginxConfigDto nginxConfig){
        StringBuilder sb = new StringBuilder();
        // HTTP를 HTTPS로 리디렉션하는 서버 블록
        sb.append("server {\n");
        sb.append("    listen 80;\n"); //80번 포트로 요청이 들어왔을때
        sb.append("    listen [::]:80;\n");
        sb.append("\n");
        sb.append("    server_name "+nginxConfig.getDomain()+";\n");
        sb.append("    index index.html index.htm index.nginx-debian.html;\n");
        sb.append("\n");

        sb.append("    location /.well-known/acme-challenge/ {\n");
        sb.append("        allow all;\n");
        sb.append("        root /usr/share/nginx/html;\n");
        sb.append("    }\n");
        sb.append("\n");

        sb.append("    location / {\n");
        sb.append("        return 301 https://$host$request_uri;\n"); //443포트로 보내기(https적용할 수 있도록)
        sb.append("    }\n");
        sb.append("}\n");
        sb.append("\n");

        // HTTPS를 처리하는 서버 블록
        sb.append("server {\n");
        sb.append("    listen 443 ssl;\n"); // 443번 포트로 HTTPS 요청이 들어왔을 때, SSL을 사용한다는 의미
        sb.append("    listen [::]:443 ssl;\n");
        sb.append("    server_name "+nginxConfig.getDomain()+";\n"); // 사용할 도메인 설정
        sb.append("    index index.html index.htm index.nginx-debian.html;\n");
        sb.append("\n");
        sb.append("    ssl_certificate "+nginxConfig.getSslCertificate()+"\n"); // SSL 인증서 설정
        sb.append("    ssl_certificate_key "+nginxConfig.getSslCertificateKey()+"\n"); // SSL 인증서 키 설정
        //보안 강화 추가
        sb.append("\n");

        //locations 리스트 for문 통해서 config 작성
        for(NginxProxyDto proxyConfig : nginxConfig.getProxyList()){
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
            sb.append("        client_max_body_size 100M;\n");
            sb.append("\n");
            sb.append("        proxy_read_timeout 300;\n");// 프록시 읽기 시간 제한 설정
            sb.append("    }\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    //https 사용안하는 nginx config 생성
    @Override
    public String withoutHttpsConfig(NginxConfigDto nginxConfig){
        StringBuilder sb = new StringBuilder();
        sb.append("server {\n");
        sb.append("    listen 80;\n");
        sb.append("    listen [::]:80;\n");
        sb.append("\n");
        sb.append("    server_name "+nginxConfig.getDomain()+";\n");
        sb.append("    index index.html index.htm index.nginx-debian.html;\n");
        sb.append("\n");
        for(NginxProxyDto proxyConfig : nginxConfig.getProxyList()){
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
            sb.append("        client_max_body_size 100M;\n");
            sb.append("\n");
            sb.append("        proxy_read_timeout 300;\n");
            sb.append("    }\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    //프론트 nginx config 생성
    @Override
    public String createFrontNginxConfig(){
        StringBuilder sb = new StringBuilder();
        sb.append("server {\n");
        sb.append("    listen 80;\n"); // 80번 포트로 들어오는 요청 수신
        sb.append("    client_max_body_size 10M;\n"); // 클라이언트로부터 최대 10MB까지의 요청 본문 허용
        sb.append("    location / {\n") // 모든 경로에 대한 설정
                .append("        root /usr/share/nginx/html;\n") // 정적 파일들이 위치한 디렉토리 설정
                .append("        index index.html index.htm;\n") // index 파일 설정
                .append("        try_files $uri $uri/ /index.html;\n") // 요청된 파일이 존재하지 않을 경우 index.html 파일 서빙
                .append("    }\n");
        sb.append("\n");
        sb.append("    error_page   500 502 503 504  /50x.html;\n"); // 서버 에러 발생 시 50x.html로 리다이렉트
        sb.append("\n");
        sb.append("    location = /50x.html {\n") // 50x.html 페이지 설정
                .append("        root /usr/share/nginx/html;\n")  // 50x.html 파일이 위치한 디렉토리 설정
                .append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }
}
