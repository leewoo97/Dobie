package com.dobie.backend.domain.project.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectGetResponseDto {

    private int projectId;
    private String projectName;
}
