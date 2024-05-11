package com.dobie.backend.domain.project.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectWithFileRequestDto {
    private String projectName;

    private String projectDomain;
    private boolean usingHttps;

    private GitRequestDto git;
    private Map<String, BackendRequestDto> backendMap;
    private FrontendRequestDto frontend;
    private Map<String, DatabaseRequestDto> databaseMap;
    private List<String> filePathList;

    private List<MultipartFile> files;
}
