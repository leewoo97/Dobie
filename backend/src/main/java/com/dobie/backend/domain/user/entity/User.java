package com.dobie.backend.domain.user.entity;

import com.dobie.backend.domain.user.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    public User(UserDto dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
    }
}
