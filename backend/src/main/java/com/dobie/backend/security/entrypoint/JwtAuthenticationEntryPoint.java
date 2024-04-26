package com.dobie.backend.security.entrypoint;

import com.dobie.backend.security.response.FilterResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

//Spring Security에서 인증 과정 중 발생하는 예외를 처리하는 역할
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final FilterResponse filterResponse; //인증 관련 오류 응답 메시지 처리 담당 컴포넌트

    @Override           //인증에 실패했을 때 호출되는 메서드
    public void commence(HttpServletRequest request, //현재 http 요청 정보 포함
                         HttpServletResponse response, //현재 http 응답 정보 수정
                         AuthenticationException authException) throws IOException, ServletException { //발생한 인증 관련 예외
        filterResponse.requestNewToken(response);

    }
}
