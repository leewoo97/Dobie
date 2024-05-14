package com.dobie.backend.domain.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitRequestDto {
    private int gitType;
    private String gitUrl;
    private String branch;
    private String accessToken;
}
