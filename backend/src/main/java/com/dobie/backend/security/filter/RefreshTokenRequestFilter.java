package com.dobie.backend.security.filter;

import com.dobie.backend.security.response.FilterResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//URL(/api/user)에서 PUT 요청이 있는 경우 리프레시 토큰의 존재 여부 확인 후 존재하지 않는다면 토큰 재발급을 유도 메시지 전송
@Log4j2
@Component
@RequiredArgsConstructor
public class RefreshTokenRequestFilter extends OncePerRequestFilter {

    private final FilterResponse filterResponse;
    final String TOKEN_URL = "/api/user";
    final String PUT_METHOD = "PUT";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 토큰 갱신 요청 확인. (요청 URL과 메소드가 지정된 값과 일치하는지 검사)
        if (!(request.getRequestURI().equals(TOKEN_URL) && request.getMethod().equals(PUT_METHOD))) {
            filterChain.doFilter(request, response); // 일치하지 않으면, 요청을 다음 필터로 넘김
            return;
        }

        Cookie[] cookies = request.getCookies();// 요청에서 쿠키 배열 추출
        boolean refreshTokenPresent = isConfirmedRefreshTokenPresent(cookies);// "RefreshToken" 쿠키의 존재 여부를 확인

        log.info("action=TokenRefreshAttempt, method={}, uri={}, refreshTokenPresent={}",
                request.getMethod(), request.getRequestURI(), refreshTokenPresent);//Refresh토큰 존재 여부 로깅

        // Refresh 토큰이 존재하지 않는 경우 응답 처리
        if (!refreshTokenPresent) {
            filterResponse.tokenInvalidResponse(response); // 토큰 재발급 요청 응답
            log.info("event=RefreshTokenMissing, action=BlockRequest");
            return;
        }
        filterChain.doFilter(request, response);//Refresh 토큰 존재하는 경우 다음 필터 실행
    }
    //쿠키 배열에서 "RefreshToken" 쿠키의 존재 여부를 확인
    private boolean isConfirmedRefreshTokenPresent(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if ("RefreshToken".equals(cookie.getName())) {
                return true;
            }
        }
        return false;
    }

}
