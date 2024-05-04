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

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final ObjectMapper mapper;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenManager tokenManager;
    private final TokenService tokenService;
    private final CookieUtil cookieUtil;

    @Override
    public String getPrettyJsonString(JsonNode node) {

        try {
            // 이쁘게 print하기 위해 writerWithDefaultPrettyPrinter 추가
            // 이쁜 print 가 필요없다면 mapper.writeValueAsString(node); 사용해도 됨
            String prettyJson = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(node);

            log.info("pretty Print Result...\n{}",prettyJson);

            return prettyJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDto getUserInfo() {
        User user = userRepository.getUserInfo();
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    @Override
    public void changeUserInfo(UserDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = new User(dto);
        userRepository.updateUserInfo(user);
    }

    @Override
    public LoginResponseDto login(UserDto dto, HttpServletResponse response){
        User user = userRepository.getUserInfo();
        if(passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            log.info("Password matched for user: {}", user.getUsername());
            removeOldRefreshToken(dto, user); //리프레시토큰 삭제

            TokenInfo tokenInfo = tokenManager.generateTokenInfo(user.getUsername());
            tokenService.saveToken(tokenInfo);
            cookieUtil.addCookie("RefreshToken", tokenInfo.getRefreshToken(), tokenManager.getREFRESH_TOKEN_TIME(), response);

            UserDto userDto = UserDto.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
            return LoginResponseDto.builder()
                    .refreshToken(tokenInfo.getRefreshToken())
                    .accessToken(tokenInfo.getAccessToken())
                    .userDto(userDto)
                    .build();
        }else {
            log.info("Login failed for user: {}", dto.getUsername());
            throw new BadCredentialsException("Invalid username or password");
        }

    }

    @Override
    public void removeOldRefreshToken(UserDto dto, User user){
        refreshTokenRepository.findByRefreshToken(user.getUsername())
                .ifPresent(refreshTokenRepository::delete);
        log.info("event=DeleteExistingRefreshToken, username={}", dto.getUsername());
    }
}
