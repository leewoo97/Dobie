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
}
