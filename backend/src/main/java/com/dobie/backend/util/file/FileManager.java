package com.dobie.backend.util.file;

import com.dobie.backend.exception.exception.file.DeleteFileFailedException;
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

    public void createFolder(String path, String fileName, String contents) {
        File directory = new File(path);
        if (!directory.exists()) {
            boolean result = directory.mkdir();  // 하나의 디렉토리를 생성
            // 또는 directory.mkdirs(); // 여러 중첩된 디렉토리를 생성
            if (result) {
                System.out.println("Folder created successfully.");
            } else {
                System.out.println("Failed to create folder.");
            }
        }

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

    public void deleteFile(String path, String filename) {
        // 파일 객체 생성
        File file = new File(path, filename);

        // 파일 존재 여부 확인 및 삭제 시도
        if (file.exists()) {
            boolean result = file.delete();
        } else {
            throw new DeleteFileFailedException("File Delete Failed");
        }
    }
}
