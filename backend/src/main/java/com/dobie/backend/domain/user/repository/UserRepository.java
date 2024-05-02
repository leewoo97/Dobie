package com.dobie.backend.domain.user.repository;

import com.dobie.backend.domain.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final ObjectMapper mapper;

    private static final String BASE_PATH = new File("").getAbsolutePath();
    private static final String RESOURCE_PATH = "/src/main/resources";
    private static final String FILE_NAME = "/data/user.json";
    private static final String FILE_PATH =
            Files.exists(Paths.get(BASE_PATH + RESOURCE_PATH + FILE_NAME)) ? BASE_PATH + RESOURCE_PATH + FILE_NAME : BASE_PATH + FILE_NAME;


    public User getUserInfo() {
        try{
            File file = new File(FILE_PATH);
            return mapper.readValue(file, User.class);
        }catch (IOException e ){
            e.getStackTrace();
        }

        return null;
    }

    public void updateUserInfo(User user){
        try{
            File file = new File(FILE_PATH);

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, user);
        }catch (IOException e ){
            e.getStackTrace();
        }
    }
}
