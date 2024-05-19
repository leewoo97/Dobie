package com.dobie.backend.domain.user.controller;

import com.dobie.backend.domain.user.dto.LoginResponseDto;
import com.dobie.backend.domain.user.dto.UserDto;
import com.dobie.backend.domain.user.entity.User;
import com.dobie.backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUserInfo() {
        UserDto user = userService.getUserInfo();
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDto dto){
        userService.changeUserInfo(dto);
        return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
    }

    @Operation(summary = "로그인", description = "login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto dto,HttpServletResponse httpServletResponse){

        try {
            LoginResponseDto loginResponse = userService.login(dto, httpServletResponse);
            return ResponseEntity.ok().body(loginResponse); // 로그인 성공시, 로그인 관련 데이터와 함께 성공 응답 반환
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: " + e.getMessage()); // 로그인 실패 시, 에러 메시지 반환
        }
    }

}
