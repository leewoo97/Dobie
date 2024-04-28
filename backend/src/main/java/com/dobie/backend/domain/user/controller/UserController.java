package com.dobie.backend.domain.user.controller;

import com.dobie.backend.domain.user.dto.UserDto;
import com.dobie.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
