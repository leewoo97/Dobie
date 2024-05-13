package com.dobie.backend.domain.project.dto.file;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilePostDto {

    private String projectId;
    private String projectName;
    private List<FileGetDto> fileList;

}
