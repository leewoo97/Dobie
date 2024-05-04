package com.dobie.backend.domain.user.service;

import com.dobie.backend.domain.user.dto.LoginResponseDto;
import com.dobie.backend.domain.user.dto.UserDto;
import com.dobie.backend.domain.user.entity.User;
import com.dobie.backend.domain.user.repository.UserRepository;
import com.dobie.backend.security.jwt.TokenManager;
import com.dobie.backend.security.jwt.TokenService;
import com.dobie.backend.security.jwt.dto.TokenInfo;
import com.dobie.backend.security.jwt.repository.RefreshTokenRepository;
import com.dobie.backend.util.CookieUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.AuthenticationException;


public interface UserService {

    public String getPrettyJsonString(JsonNode node);

    public UserDto getUserInfo();

    public void changeUserInfo(UserDto dto);

    public LoginResponseDto login(UserDto dto, HttpServletResponse response);

    private void removeOldRefreshToken(UserDto dto, User user) {
    }
}
