package com.dobie.backend.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//암호화 키를 사용하여 토큰을 생성하고 유효성 검증하는 클래스
@Log4j2
@Component
public class TokenManager {

    @Getter
    private final int ACCESS_TOKEN_TIME; // 액세스 토큰 유효 시간
    @Getter
    private final int REFRESH_TOKEN_TIME; // 리프레시 토큰 유효 시간
    private final String dobieisfree; // 시크릿 키(jwt 서명하는데 사용되는 비밀키)
    private Key key; // 암호화 키(JWT 서명을 생성하고 검증하는데 실제로 사용되는 암호화 키)

    //@Value는 application-jwt.yaml(설정파일)의 ACCESS_TIME,REFRESH_TIME,SECRET_KEY라는 지정된 값을 자바 코드 내(@Value 어노테이션이 쓰인 클래스)의 변수에 자동으로 주입하는 역할을 수행함
    public TokenManager(@Value("${jwt.ACCESS_TIME}") final int ACCESS_TOKEN_TIME,
                         @Value("${jwt.REFRESH_TIME}") final int REFRESH_TOKEN_TIME,
                         @Value("${jwt.SECRET_KEY}") final String SECRET_KEY) {// 토큰 서명에 사용되는 비밀 키
        this.ACCESS_TOKEN_TIME = ACCESS_TOKEN_TIME;
        this.REFRESH_TOKEN_TIME = REFRESH_TOKEN_TIME;
        this.dobieisfree = SECRET_KEY;
        //키 생성: 인코딩된 dobieisfree(SECRET_KEY)를 Base64 디코딩하여, JWT 서명에 사용할 암호화키로 변환함
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(this.dobieisfree));
//        System.out.println(this.key);
    }
    //TokenInfo 객체 생성(객체에 액세스 토큰과 리프레시 토큰이 포함되어 있음)
    public TokenInfo generateTokenInfo(String username) {
        return TokenInfo.builder()
                .username(username)
                .accessToken(createAccessToken(username))
                .refreshToken(createRefreshToken())
                .build(); //필요한 토큰들을 생성하고 이를 TokenInfo 객체에 담아 반환
    }
    // 액세스 토큰을 생성
    private String createAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)// 토큰의 주체로 username 설정
                .setExpiration(new Date(
                        System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(ACCESS_TOKEN_TIME)))
                .signWith(key)// 생성된 키로 토큰 서명
                .compact();// 생성된 JWT를 문자열로 압축
    }
    // 리프레시 토큰을 생성
    private String createRefreshToken() {
        return Jwts.builder()
                .setExpiration(new Date(
                        System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(REFRESH_TOKEN_TIME)))// 토큰의 만료 시간 설정
                .signWith(key)// 생성된 키로 토큰 서명
                .compact(); // 생성된 JWT를 문자열로 압축
    }
    // 주어진 토큰에서 클레임(Claims)을 추출
    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)// 서명 검증을 위한 키 설정
                    .build()
                    .parseClaimsJws(token)// JWT를 파싱하여 클레임 추출
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰, 사용자의 username : {}", e.getClaims().getSubject());
            return e.getClaims();
        } catch (JwtException e) {
            log.error("토큰 파싱 실패: {}", e.getMessage());
            throw new RuntimeException("토큰 파싱 실패: {}", e);
        }
    }
    // 토큰에서 subject 클레임(사용자의 고유 식별자)을 추출하는 데 사용
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    // 토큰 유효성 검증 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;// 토큰이 유효하면 true 반환
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (MalformedJwtException e) {
            log.info("잘못된 형식의 JWT 토큰입니다.");
        } catch (SignatureException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        } catch (JwtException e) {
            log.info("JWT 토큰 처리 중 알 수 없는 예외가 발생했습니다.");
        }
        return false;
    }

}
