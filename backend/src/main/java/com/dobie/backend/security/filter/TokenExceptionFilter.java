package com.dobie.backend.security.filter;

import com.dobie.backend.security.response.FilterResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//JwtAuthenticationFilter에 의해 설정된 보안 컨텍스트에 따라 처리되는 요청 중 인증 오류가 발생할 때 그 오류를 처리하는 필터
@Log4j2
@Component
@RequiredArgsConstructor
public class TokenExceptionFilter extends OncePerRequestFilter {

    private final FilterResponse filterResponse;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (UnauthorizedAccessException e) {
            log.error("TokenExceptionFilter : Unauthorized access attempt at [{}] -> \nException Message: {}",
                    request.getRequestURI(), e.getMessage(), e);
            // 응답 처리: 로그인이 필요함을 알리는 응답 반환
            filterResponse.sendLoginRequiredResponse(response);
        }

    }
}
