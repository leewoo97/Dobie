package com.dobie.backend.domain.user.repository;

import com.dobie.backend.domain.user.dto.UserDto;
import com.dobie.backend.domain.user.entity.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
@RequiredArgsConstructor
public class UserJsonRepository {

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

    public void updateUserInfo(UserDto dto){
        try{
            File file = new File(System.getProperty("user.dir")+"/data/user.json");
//            ObjectNode userInfoNode = (ObjectNode) mapper.readTree(file);
            JsonNode userInfoJson = mapper.convertValue(dto, JsonNode.class);

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, userInfoJson);
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
