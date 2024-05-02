package com.dobie.backend.domain.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseRequestDto {
    private String databaseType;
    private String databaseName;

    private String schemaPath;

    private String username;
    private String password;

    private int externalPort;
    private int internalPort;
}
