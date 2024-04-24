package com.dobie.backend.domain.project.controller;

import com.dobie.backend.domain.project.dto.UserResponseDto;
import com.dobie.backend.domain.project.service.UserJsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/json")
public class JsonController {

    private final UserJsonService userJsonService;

    @GetMapping("/test")
    public ResponseEntity<?> getUserInfo() {
        UserResponseDto user = userJsonService.getUserInfo();
        return new ResponseEntity<UserResponseDto>(user, HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<?> changePassword(@RequestBody String password){
        userJsonService.changePassword(password);
        return new ResponseEntity<String>(password, HttpStatus.OK);
    }

}
