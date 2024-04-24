package com.dobie.backend.domain.project.service;

import com.dobie.backend.domain.project.dto.UserResponseDto;
import com.dobie.backend.domain.project.entity.User;
import com.dobie.backend.domain.project.repository.UserJsonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserJsonService {

    private final ObjectMapper mapper;

    private final UserJsonRepository userJsonRepository;
    public String getPrettyJsonString(JsonNode node) {

        try {
            // 이쁘게 print하기 위해 writerWithDefaultPrettyPrinter 추가
            // 이쁜 print 가 필요없다면 mapper.writeValueAsString(node); 사용해도 됨
            String prettyJson = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(node);

            log.info("pretty Print Result...\n{}",prettyJson);

            return prettyJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserResponseDto getUserInfo() {
        User user = userJsonRepository.getUserInfo();
        UserResponseDto dto = UserResponseDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        return dto;
    }

    public void changePassword(String password) {
        userJsonRepository.updatePassword(password);
    }
}
