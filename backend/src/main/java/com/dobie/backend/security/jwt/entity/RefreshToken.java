package com.dobie.backend.security.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RefreshToken {

    private String username;
    private String refreshToken;
    private String accessToken;

    // 엑세스 토큰 갱신
    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
