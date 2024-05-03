package com.dobie.backend.domain.project.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRequestDto {
    private String projectName;

    private String projectDomain;
    private boolean usingHttps;

    private GitRequestDto git;
    private Map<String, BackendRequestDto> backendMap;
    private FrontendRequestDto frontend;
    private Map<String, DatabaseRequestDto> databaseMap;
}
