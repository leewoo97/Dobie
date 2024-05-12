package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Database;
import com.dobie.backend.domain.project.entity.File;
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
public class FileGetResponseDto {
    private String fileId;
    private String filePath;
    private String fileName;

    public FileGetResponseDto(File file) {
        this.fileId = file.getFileId();
        this.filePath = file.getFilePath();
        this.fileName = file.getFileName();
    }
}
