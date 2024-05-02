package com.dobie.backend.security.filter;

import com.dobie.backend.security.response.FilterResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//다른 필터나 컴포넌트에서 발생할 수 있는 UnauthorizedAccessException을 캐치하고, 사용자에게 로그인이 필요함을 알리는 응답을 반환하는 필터
@Log4j2
@Component
@RequiredArgsConstructor
public class TokenExceptionFilter extends OncePerRequestFilter {

    private final FilterResponse filterResponse;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            log.error("Unauthorized Access Detected: Request to [{}] was blocked due to failed authentication. Action Required: User re-authentication. Detailed Error: {}",
                    request.getRequestURI(), e.getLocalizedMessage());
            // 응답 처리: 로그인이 필요함을 알리는 응답 반환
            filterResponse.sendLoginRequiredResponse(response);
        }

    }
}
