package com.dobie.backend.domain.nginx.service;

import com.dobie.backend.domain.nginx.dto.NginxConfigGetResponseDto;

import java.io.IOException;

public interface NginxConfigService {
    String createReverseProxyNginxConfig(NginxConfigGetResponseDto nginxConfig);

    String withHttpsConfig(NginxConfigGetResponseDto nginxConfig);

    String withoutHttpsConfig(NginxConfigGetResponseDto nginxConfig);

    void saveConfigFile(String path, String projectName) throws IOException;

    String createFrontNginxConfig();
}
