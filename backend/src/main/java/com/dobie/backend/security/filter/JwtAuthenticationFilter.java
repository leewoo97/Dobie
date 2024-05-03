package com.dobie.backend.security.filter;

import com.dobie.backend.security.jwt.TokenManager;
import com.dobie.backend.security.jwt.TokenService;
import com.dobie.backend.security.response.FilterResponse;

import com.dobie.backend.util.CookieUtil;
import com.dobie.backend.util.FilterUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

// HTTP 요청을 가로채 JWT의 유효성을 검사하고, 유효한 경우 사용자의 인증 정보를 SecurityContext에 설정하는 필터
// OncePerRequestFilter에 의해 Spring Security 필터 체인에서 한 번만 실행됨(특정 요청은 필터링x)
@Log4j2
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CookieUtil cookieUtil; // 쿠키를 처리하는 유틸리티 클래스의 인스턴스
    private final FilterUtil filterUtil;// 필터링할 URL 패턴을 관리하는 클래스의 인스턴스
    private final TokenService tokenService;// 토큰 저장 및 관리를 제공하는 클래스의 인스턴스
    private final TokenManager tokenManager ;// 토큰 생성 및 검증을 담당하는 클래스의 인스턴스
    private final FilterResponse filterResponse;// HTTP 응답 관련 유틸리티를 제공하는 클래스의 인스턴스

    private AntPathMatcher pathMatcher; // URL 패턴 매칭에 사용
    private Map<String, String> filterPaths; //URL이 필터링을 건너뛸 패턴(ex./api/public/**)에 해당하는지 여부를 결정

    @PostConstruct //모든 @Autowired or 생성자 주입이 끝나고 이 메서드가 호출되어 추가적인 초기화 작업을 수행( 서버가 시작될 때 한 번만 호출됨)
    public void init() {
        this.filterPaths = filterUtil.getPaths(); //FilterUtil로부터 필터링할 URL 패턴을 로드해옴
        this.pathMatcher = new AntPathMatcher(); //주어진 URL이 바로 윗줄(FilterUtil 클래스에서 관리되는 URL 패턴)에서 언급한 패턴과 일치하는지를 확인할 때 사용
    }

    //실제 필터링 로직
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("URI: {} {}", request.getMethod(), request.getRequestURI());

        //요쳥된 uri가 필터링할 필요가 없는 패턴인지 검사 후 필터링 할 필요 없으면 필터 체인 계속 진행
        if (shouldFilter(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = tokenService.extractAccessToken(request);// 요청에서 액세스 토큰 추출
        String refreshToken = cookieUtil.getRefreshTokenValue(request);// 요청에서 리프레시 토큰 추출

        boolean accessTokenValid = (accessToken != null) && tokenManager.validateToken(accessToken);// 액세스 토큰의 유효성 검사
        boolean refreshTokenValid = (refreshToken != null) && tokenManager.validateToken(refreshToken);// 리프레시 토큰의 유효성 검사

        String username = tokenManager.extractUsername(accessToken);

        //accessToken, refreshToken 모두 유효하지 않을 때, 클라이언트에게 다시 로그인하도록 요청 응답 보냄
        if (!accessTokenValid && !refreshTokenValid) {
            log.warn("access_token_valid={}, refresh_token_valid={}, action={}, status={}",
                    false, false, "DENY_ACCESS", "BOTH_TOKENS_INVALID");
            filterResponse.tokenInvalidResponse(response);
        }
        //accessToken이 만료되었지만 refreshToken은 유효한 경우, 클라이언트에게 새로운 accessToken을 발급받을 수 있도록 유도하는 응답을 보냄
        else if (!accessTokenValid) {
            log.info("access_token_valid={}, refresh_token_valid={}, action={}, username={}, status={}",
                    false, true, "ISSUE_NEW_TOKEN",username,"ACCESS_TOKEN_EXPIRED");
            filterResponse.sendTokenRefreshRequest(response);
        }
        //accessToken이 유효하여 인증에 성공한 경우 (refreshToken의 유효성은 이 시나리오에서는 직접적인 영향을 주지 않음)
        // -> 따라서 사용자는 요청을 계속 진행할 수 있음
        else {
            String action = refreshTokenValid ? "CONTINUE_WITH_REQUEST" : "CONTINUE_WITH_EXPIRED_REFRESH";
            String status = refreshTokenValid ? "BOTH_TOKENS_VALID" : "REFRESH_TOKEN_EXPIRED";
            log.info("access_token_valid={}, refresh_token_valid" +
                            "={}, action={}, username={}, status={}",
                    true, refreshTokenValid, action, username, status);
            setAuthentication(username, request); //액세스 토큰이 유효하면 보안 컨텍스트에 인증 정보를 설정함
            filterChain.doFilter(request, response);//요청을 다음 필터 체인으로 전달
        }
    }
    // 특정 URI 패턴에 대해서는 필터링(토큰 검증)을 건너뛰어야 할 때 사용함
    private boolean shouldFilter(String path) {
        return filterPaths.values().stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }
    //사용자가 인증된 상태임을 시스템에 등록함
    private void setAuthentication(String username, HttpServletRequest request) {
        // 이메일 주소를 기반으로 UsernamePasswordAuthenticationToken 생성
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        username, // 주체
                        "", // 자격증명, 비밀번호 등의 민감 정보는 여기에 포함하지 않음
                        Collections.singleton(new SimpleGrantedAuthority("ADMIN")) // 사용자 권한 설정
                );
        // 요청 세부 정보를 인증 객체에 설정
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // SecurityContext에 인증 객체를 저장하여, 이 후 요청에서 사용자가 인증된 상태임을 확인할 수 있음
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
