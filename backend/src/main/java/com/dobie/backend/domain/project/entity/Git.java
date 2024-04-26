package com.dobie.backend.domain.project.entity;

import com.dobie.backend.domain.project.dto.GitRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Git {
    private int gitType;
    private String gitUrl;
    private String accessToken;

    public Git(GitRequestDto dto){
        this.gitType = dto.getGitType();
        this.gitUrl = dto.getGitUrl();
        this.accessToken = dto.getAccessToken();
    }
}
