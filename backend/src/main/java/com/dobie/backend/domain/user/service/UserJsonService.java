package com.dobie.backend.domain.user.service;

import com.dobie.backend.domain.user.dto.UserDto;
import com.dobie.backend.domain.user.entity.User;
import com.dobie.backend.domain.user.repository.UserJsonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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

    public UserDto getUserInfo() {
        User user = userJsonRepository.getUserInfo();
        UserDto dto = UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        return dto;
    }

    public void changeUserInfo(UserDto dto) {
        userJsonRepository.updateUserInfo(dto);
    }
}
