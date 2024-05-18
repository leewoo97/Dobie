package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Frontend;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrontendGetResponseDto {
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

    private boolean usingNginx;
    public FrontendGetResponseDto(Frontend frontend) {
        this.serviceId = frontend.getServiceId();
        this.serviceName = frontend.getServiceName();
        this.language = frontend.getLanguage();
        this.version = frontend.getVersion();
        this.framework = frontend.getFramework();
        this.path = frontend.getPath();
        this.branch = frontend.getBranch();
        this.location = frontend.getLocation();
        this.externalPort = frontend.getExternalPort();
        this.internalPort = frontend.getInternalPort();
        this.usingNginx = frontend.isUsingNginx();
    }
}
