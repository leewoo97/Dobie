package com.dobie.backend.domain.project.dto;

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
public class FileCheckDto {
    private String fileId;
    private String filePath;
    private String fileName;
    private boolean isExist;

    public FileCheckDto(FileGetResponseDto dto, boolean b) {
        this.fileId = dto.getFileId();
        this.filePath = dto.getFilePath();
        this.fileName = dto.getFileName();
        this.isExist = b;
    }
}
