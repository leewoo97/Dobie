package com.dobie.backend.domain.user.repository;

import com.dobie.backend.domain.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final ObjectMapper mapper;

    public User getUserInfo() {
        try{
            File file = new File(System.getProperty("user.dir")+"/data/user.json");
            return mapper.readValue(file, User.class);
        }catch (IOException e ){
            e.printStackTrace();
        }

        return null;
    }

    public void updateUserInfo(User user){
        try{
            File file = new File(System.getProperty("user.dir")+"/data/user.json");

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, user);
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
