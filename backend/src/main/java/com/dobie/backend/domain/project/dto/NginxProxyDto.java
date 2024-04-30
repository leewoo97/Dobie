package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Backend;
import com.dobie.backend.domain.project.entity.Frontend;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NginxProxyDto {
    private String location;
    private String serviceName;
    private int port;

    public NginxProxyDto(Backend backend){
        this.location = backend.getLocation();
        this.serviceName = backend.getServiceName();
        this.port = backend.getInternalPort();
    }

    public NginxProxyDto(Frontend frontend){
        this.location = frontend.getLocation();
        this.serviceName = frontend.getServiceName();
        this.port = frontend.getInternalPort();
    }
}
