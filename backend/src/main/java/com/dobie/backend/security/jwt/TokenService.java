package com.dobie.backend.security.jwt;

import com.dobie.backend.security.jwt.repository.RefreshToken;
import com.dobie.backend.security.jwt.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.dobie.backend.security.jwt.TokenManager;

// 토큰의 저장 및 관리와 관련된 비즈니스 로직을 처리하는 클래스
// ex.데이터베이스에 토큰을 저장/삭제, 토큰의 정보를 조회하는 작업 수행
// TokenManager를 사용하여 필요한 토큰을 생성하거나 검증할 수 있지만, 주로 토큰과 관련된 데이터의 관리를 책임짐
@Log4j2
@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenManager tokenManager; // 토큰 생성 및 검증을 담당하는 클래스
    private final RefreshTokenRepository refreshTokenRepository; // 리프레시 토큰 저장소

    // 리프레시 토큰 저장
    public void saveToken(TokenInfo tokenInfo) {
        refreshTokenRepository.save(RefreshToken.builder()
                .username(tokenInfo.getUsername())
                .refreshToken(tokenInfo.getRefreshToken())
                .accessToken(tokenInfo.getAccessToken())
                .build());
    }
    // 리프레시토큰 삭제
    public void removeToken(String refreshToken) {
        refreshTokenRepository.findByRefreshToken(refreshToken).ifPresent(refreshTokenRepository::delete);
    }

//    public RefreshToken getByRefreshToken(String refreshToken) {
//        return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(RefreshTokenNotFoundException::new);
//    }

    //액세스 토큰 재발급
    public String reIssueAccessToken(String refreshToken) {
        String username = tokenManager.extractUsername(refreshToken);
        String newAccessToken = tokenManager.createAccessToken(username);
        refreshTokenRepository.updateAccessToken(newAccessToken);
        return newAccessToken;
    }

    //요청에서 액세스 토큰 추출
    public String extractAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }

    public String extractAccessToken(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }

}
