package com.dobie.backend.domain.nginx.service;

import com.dobie.backend.domain.project.dto.NginxConfigDto;

import java.io.IOException;

public interface NginxConfigService {
    void saveProxyNginxConfig(String projectId, boolean usingNginx, String frontServiceId);

    String withHttpsConfig(NginxConfigDto nginxConfig,boolean usingNginx, String frontServiceId);

    String withoutHttpsConfig(NginxConfigDto nginxConfig,boolean usingNginx, String frontServiceId);

    void saveFrontNginxConfigFile(String path, String projectName) throws IOException;

    String createFrontNginxConfig();

    String readNginxFile(String filePath) throws IOException;
}
