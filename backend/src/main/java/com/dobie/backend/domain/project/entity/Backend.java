package com.dobie.backend.domain.project.entity;

import com.dobie.backend.domain.project.dto.BackendRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Backend {
    private String serviceId;
    private String serviceName;

    private String language;
    private String version;
    private String framework;

    private String path;
    private String branch;

    private int externalPort;
    private int internalPort;

    public Backend(String serviceId, BackendRequestDto dto){
        this.serviceId = serviceId;
        this.serviceName = dto.getServiceName();
        this.language = dto.getLanguage();
        this.version = dto.getVersion();
        this.framework = dto.getFramework();
        this.path = dto.getPath();
        this.branch = dto.getBranch();
        this.externalPort = dto.getExternalPort();
        this.internalPort = dto.getInternalPort();
    }
}
