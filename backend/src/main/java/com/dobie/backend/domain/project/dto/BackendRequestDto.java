package com.dobie.backend.domain.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackendRequestDto {
    private String serviceName;

    private String language;
    private String version;
    private String framework;

    private String path;
    private String branch;

    private String location;

    private int externalPort;
    private int internalPort;

    @Override
    public String toString() {
        return "BackendRequestDto{" +
                "serviceName='" + serviceName + '\'' +
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
