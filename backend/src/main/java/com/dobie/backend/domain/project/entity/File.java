package com.dobie.backend.domain.project.entity;

import com.dobie.backend.domain.project.dto.BackendRequestDto;
import com.dobie.backend.domain.project.dto.FileRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private String fileId;

    private String filePath;
    private String fileName;

    public File(String fileId, FileRequestDto dto){
        this.fileId = fileId;
        this.filePath = dto.getFilePath();
        this.fileName = dto.getFileName();
    }

}
