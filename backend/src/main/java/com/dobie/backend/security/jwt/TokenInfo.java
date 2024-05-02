package com.dobie.backend.security.jwt;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenInfo {

    private String username;
    private String accessToken;
    private String refreshToken;

}
