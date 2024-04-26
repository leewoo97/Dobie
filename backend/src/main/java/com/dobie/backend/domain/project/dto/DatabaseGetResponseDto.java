package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Database;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseGetResponseDto {
    private String databaseType;
    private String username;
    private String password;

    private int externalPort;
    private int internalPort;
    public DatabaseGetResponseDto(Database database) {
        this.databaseType = database.getDatabaseType();
        this.username = database.getUsername();
        this.password = database.getPassword();
        this.externalPort = database.getExternalPort();
        this.internalPort = database.getInternalPort();
    }
}
