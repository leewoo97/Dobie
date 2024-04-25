package com.dobie.backend.domain.user.controller;

import com.dobie.backend.domain.user.dto.UserDto;
import com.dobie.backend.domain.user.service.UserJsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserJsonController {

    private final UserJsonService userJsonService;

    @GetMapping("/get")
    public ResponseEntity<?> getUserInfo() {
        UserDto user = userJsonService.getUserInfo();
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDto dto){
        userJsonService.changeUserInfo(dto);
        return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
    }

}
