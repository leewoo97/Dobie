package com.dobie.backend.security.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@Component
public class FilterResponse {

    // http 응답을 구성하고 클라이언트에 JSON 형태로 메시지 전송하는 메소드
    private void sendJsonResponse(HttpServletResponse response, int status, String statusMessage, String message, Optional<String> actionRequired) throws IOException {
        log.info("Sending JSON response - Status: {}, Message: {}", statusMessage, message);
        response.setStatus(status);
        response.setContentType("application/json"); //응답 컨텐트 타입 : JSON
        response.setCharacterEncoding("UTF-8");// 문자 인코딩 : UTF-8


        String actionRequiredJson = actionRequired.map(action -> ", \"actionRequired\": \"" + action + "\"").orElse("");
        String body = String.format("{\n" +
                "  \"status\": \"%s\",\n" +
                "  \"message\": \"%s\"%s\n" +
                "}", statusMessage, message, actionRequiredJson);
        response.getWriter().write(body);
    }

    // Spring Security에서 인증에 실패한 경우 호출되는 메서드
    public void requestNewToken(HttpServletResponse response) throws IOException{
        log.error("Token invalid. Advising retry with a new token.");
        sendJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "FAILED",
                "토큰이 유효하지 않습니다. 새로운 토큰으로 다시 시도해주세요.", Optional.empty());
    }

}
