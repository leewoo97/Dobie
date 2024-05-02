package com.dobie.backend.security.jwt.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Repository
//@RequiredArgsConstructor
public class RefreshTokenRepository {
    private ObjectMapper objectMapper = new ObjectMapper();
//    private ObjectMapper objectMapper;
    private File file = new File("refreshTokens.json");

    public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
        try {
            List<RefreshToken> tokens = objectMapper.readValue(file, new TypeReference<List<RefreshToken>>(){});
            return tokens.stream().filter(t -> refreshToken.equals(t.getRefreshToken())).findFirst();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void save(RefreshToken refreshToken) {
        try {
            List<RefreshToken> tokens = objectMapper.readValue(file, new TypeReference<List<RefreshToken>>(){});
            tokens.add(refreshToken);
            objectMapper.writeValue(file, tokens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAccessToken(String accessToken){
        try {
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
            List<RefreshToken> tokens = objectMapper.readValue(file, new TypeReference<List<RefreshToken>>(){});
            tokens.removeIf(t -> t.getRefreshToken().equals(refreshToken.getRefreshToken()));
            objectMapper.writeValue(file, tokens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
