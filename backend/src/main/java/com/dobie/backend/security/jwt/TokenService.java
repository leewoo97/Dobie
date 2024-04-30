package com.dobie.backend.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.dobie.backend.security.jwt.TokenManager;

// 토큰의 저장 및 관리와 관련된 비즈니스 로직을 처리하는 클래스
// ex.데이터베이스에 토큰을 저장/삭제, 토큰의 정보를 조회하는 작업 수행
// TokenProvider를 사용하여 필요한 토큰을 생성하거나 검증할 수 있지만, 주로 토큰과 관련된 데이터의 관리를 책임짐
@Log4j2
@Service
//@Transactional
@RequiredArgsConstructor
public class TokenService {

//    private final TokenManager tokenManager;
//    private final RefreshTokenRepository refreshTokenRepository;
//
//    public void saveToken(TokenInfo tokenInfo) {
//        refreshTokenRepository.save(RefreshToken.builder()
//                .email(tokenInfo.getEmail())
//                .refreshToken(tokenInfo.getRefreshToken())
//                .accessToken(tokenInfo.getAccessToken())
//                .build());
//    }
//
//    public void removeToken(String refreshToken) {
//        refreshTokenRepository.findByRefreshToken(refreshToken).ifPresent(refreshTokenRepository::delete);
//    }
//
//
//    @Transactional(readOnly = true)
//    public RefreshToken getByRefreshToken(String refreshToken) {
//        return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(RefreshTokenNotFoundException::new);
//    }
//
//
//    public String reIssueAccessToken(String accessToken) {
//        String email = tokenManager.extractEmail(accessToken);
//        RefreshToken refreshToken = refreshTokenRepository.findById(email)
//                .orElseThrow(RefreshTokenNotFoundException::new);
//
//        String updatedAccessToken = tokenManager.generateTokenInfo(email).getAccessToken();
//        refreshToken.updateAccessToken(updatedAccessToken);
//        refreshTokenRepository.save(refreshToken);
//        return updatedAccessToken;
//    }
//
//    public String extractAccessToken(HttpServletRequest request) {
//        String authorization = request.getHeader("Authorization");
//        if (authorization != null && authorization.startsWith("Bearer ")) {
//            return authorization.substring(7);
//        }
//        return null;
//    }
//
//    public String extractAccessToken(String authorization) {
//        if (authorization != null && authorization.startsWith("Bearer ")) {
//            return authorization.substring(7);
//        }
//        return null;
//    }

}
