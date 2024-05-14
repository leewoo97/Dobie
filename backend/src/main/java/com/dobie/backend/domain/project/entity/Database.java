package com.dobie.backend.domain.project.entity;

import com.dobie.backend.domain.project.dto.DatabaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Database {
    private String databaseId;

    private String databaseType;
    private String databaseName;

    private String schemaPath;

    private String username;
    private String password;

    private int externalPort;
    private int internalPort;

    public Database(String databaseId, DatabaseRequestDto dto){
        this.databaseId = databaseId;
        this.databaseType = dto.getDatabaseType();
        this.databaseName = dto.getDatabaseName();
        this.schemaPath = dto.getSchemaPath();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.externalPort = dto.getExternalPort();
        this.internalPort = dto.getInternalPort();
    }

    public Database(DatabaseRequestDto dto){
        this.databaseId = dto.getDatabaseId();
        this.databaseType = dto.getDatabaseType();
        this.databaseName = dto.getDatabaseName();
        this.schemaPath = dto.getSchemaPath();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.externalPort = dto.getExternalPort();
        this.internalPort = dto.getInternalPort();
    }
}
