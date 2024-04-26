package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Backend;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackendGetResponseDto {
    private int serviceId;
    private String serviceName;

    private String language;
    private String version;
    private String framework;

    private String path;
    private String branch;

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
        this.externalPort = backend.getExternalPort();
        this.internalPort = backend.getInternalPort();
    }
}
