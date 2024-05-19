package com.dobie.backend.security.jwt.repository;

import com.dobie.backend.security.jwt.entity.RefreshToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {
    private final ObjectMapper objectMapper;

    private static final String BASE_PATH = new File("").getAbsolutePath();
    private static final String RESOURCE_PATH = "/src/main/resources";
    private static final String FILE_NAME = "/data/refreshToken.json";
    private static final String FILE_PATH =
            Files.exists(Paths.get(BASE_PATH + RESOURCE_PATH + FILE_NAME)) ? BASE_PATH + RESOURCE_PATH + FILE_NAME : BASE_PATH + FILE_NAME;

    public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
        try {
            File file = new File(FILE_PATH);
            List<RefreshToken> tokens = objectMapper.readValue(file, new TypeReference<List<RefreshToken>>(){});
            return tokens.stream().filter(t -> refreshToken.equals(t.getRefreshToken())).findFirst();
        } catch (IOException e) {
            e.getStackTrace();
            return Optional.empty();
        }
    }

    public void save(RefreshToken refreshToken) {
        try {
            File file = new File(FILE_PATH);
            List<RefreshToken> tokens = objectMapper.readValue(file, new TypeReference<List<RefreshToken>>(){});
            tokens.add(refreshToken);
            objectMapper.writeValue(file, tokens);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void updateAccessToken(String accessToken){
        try {
            File file = new File(FILE_PATH);
            RefreshToken token = objectMapper.readValue(file, RefreshToken.class);
            RefreshToken newToken = RefreshToken.builder()
                    .username(token.getUsername())
                    .accessToken(accessToken)
                    .refreshToken(token.getRefreshToken())
                    .build();
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, newToken);
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    public void delete(RefreshToken refreshToken) {
        try {
            File file = new File(FILE_PATH);
            List<RefreshToken> tokens = objectMapper.readValue(file, new TypeReference<List<RefreshToken>>(){});
            tokens.removeIf(t -> t.getRefreshToken().equals(refreshToken.getRefreshToken()));
            objectMapper.writeValue(file, tokens);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
