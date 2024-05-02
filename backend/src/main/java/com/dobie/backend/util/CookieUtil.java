package com.dobie.backend.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CookieUtil {

    public CookieUtil() {
        log.info("message: CookieUtil is configured");
    }

    // 쿠키를 추가하는 메서드
    public void addCookie(String key, String value, int expiredTime, HttpServletResponse response){
        response.addHeader("Set-Cookie", createCookie(key, value, expiredTime).toString()); // 응답 헤더에 쿠키 추가
    }
    // 쿠키를 제거하는 메서드
    public void removeCookie(String key, HttpServletResponse response){
        response.addHeader("Set-Cookie", createCookie(key, null, 0).toString());// 쿠키 삭제를 위해 만료시간을 0으로 설정
    }

    private ResponseCookie createCookie(String key, String value, int expiredTime) {
        return ResponseCookie.from(key, value)// 쿠키 생성
                .httpOnly(true)// JavaScript로부터 쿠키에 접근할 수 없도록 설정(쿠키가 HTTP를 통해서만 접근될 수 있도록해서, XSS 공격에 대한 보안 강화)
                .path("/")// 쿠키가 적용될 경로 지정(쿠키가 전송되는 URL 범위 제한)
                .maxAge(expiredTime)// 쿠키의 최대 유효 시간
                .build();
    }

    //HttpServletRequest로부터 받은 쿠키 중 "RefreshToken" 이름을 가진 쿠키의 값을 찾아서 반환(해당 쿠키를 못찾으면 null 반환)
    //사용자 세션을 연장하거나, 리프레시토큰 관리하는 과정에서 쓰임(ex.액세스 토큰 만료됐을 때 이 리프레시토큰 사용해 새 액세스 토큰 발급 가능)
    public String getRefreshTokenValue(HttpServletRequest request) {
        if (request.getCookies() != null) {// 요청에 쿠키가 있는지 확인
            for (Cookie cookie : request.getCookies()) {
                if ("RefreshToken".equals(cookie.getName())) {// "RefreshToken" 이름의 쿠키를 찾음
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
