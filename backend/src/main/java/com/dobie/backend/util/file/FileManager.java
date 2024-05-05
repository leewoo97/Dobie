package com.dobie.backend.util.file;

import com.dobie.backend.exception.exception.build.DockerComposeCreateFailedException;
import com.dobie.backend.exception.exception.file.SaveFileFailedException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    public void saveFile(String path, String fileName, String contents) {
        try {
            // 파일 경로 설정
            String filePath = path + File.separator + fileName;

            // 파일 내용 작성
            Files.write(Paths.get(filePath), contents.getBytes(StandardCharsets.UTF_8));
            System.out.println("file created successfully at " + filePath);
        } catch (IOException e) {
            throw new SaveFileFailedException(e.getMessage());
        }
    }
}
