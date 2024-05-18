package com.dobie.backend.domain.project.dto.file;

import com.dobie.backend.domain.project.entity.SettingFile;
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
public class FileGetDto {

    private String fileId;
    private String filePath;
    private String fileName;

    public FileGetDto (SettingFile file){
        this.fileId = file.getFileId();
        this.filePath = file.getFilePath();
        this.fileName = file.getFileName();
    }

}
