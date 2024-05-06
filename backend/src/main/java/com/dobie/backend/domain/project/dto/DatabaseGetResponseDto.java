package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Database;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseGetResponseDto {
    private String databaseId;

    private String databaseType;
    private String databaseName;

    private String schemaPath;

    private String username;
    private String password;

    private int externalPort;
    private int internalPort;
    public DatabaseGetResponseDto(Database database) {
        this.databaseId = database.getDatabaseId();
        this.databaseType = database.getDatabaseType();
        this.databaseName = database.getDatabaseName();
        this.schemaPath = database.getSchemaPath();
        this.username = database.getUsername();
        this.password = database.getPassword();
        this.externalPort = database.getExternalPort();
        this.internalPort = database.getInternalPort();
    }
}
