package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Backend;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackendGetResponseDto {
    private String serviceId;
    private String serviceName;

    private String language;
    private String version;
    private String framework;

    private String path;
    private String branch;

    private String location;

    private int externalPort;
    private int internalPort;

    public BackendGetResponseDto(Backend backend) {
        this.serviceId = backend.getServiceId();
        this.serviceName = backend.getServiceName();
        this.language = backend.getLanguage();
        this.version = backend.getVersion();
        this.framework = backend.getFramework();
        this.path = backend.getPath();
        this.branch = backend.getBranch();
        this.location = backend.getLocation();
        this.externalPort = backend.getExternalPort();
        this.internalPort = backend.getInternalPort();
    }

    @Override
    public String toString() {
        return "BackendGetResponseDto{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", language='" + language + '\'' +
                ", version='" + version + '\'' +
                ", framework='" + framework + '\'' +
                ", path='" + path + '\'' +
                ", branch='" + branch + '\'' +
                ", externalPort=" + externalPort +
                ", internalPort=" + internalPort +
                '}';
    }
}
