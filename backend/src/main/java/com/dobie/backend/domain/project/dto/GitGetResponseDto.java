package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Git;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitGetResponseDto {
    private int gitType;
    private String gitUrl;
    private String accessToken;
    public GitGetResponseDto(Git git) {
        this.gitType = git.getGitType();
        this.gitUrl = git.getGitUrl();
        this.accessToken = git.getAccessToken();
    }
}
