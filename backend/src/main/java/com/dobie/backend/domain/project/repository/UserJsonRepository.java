package com.dobie.backend.domain.project.repository;

import com.dobie.backend.domain.project.entity.User;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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

    public void updatePassword(String password){
        try{
            File file = new File(System.getProperty("user.dir")+"/data/user.json");
            JsonNode userInfoNode = mapper.readTree(file);
            ((ObjectNode)userInfoNode).put("password", password);
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, userInfoNode);
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
