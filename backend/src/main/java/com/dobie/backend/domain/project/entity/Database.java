package com.dobie.backend.domain.project.entity;

import com.dobie.backend.domain.project.dto.DatabaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Database {
    private String databaseType;
    private String username;
    private String password;

    private int externalPort;
    private int internalPort;

    public Database(DatabaseRequestDto dto){
        this.databaseType = dto.getDatabaseType();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.externalPort = dto.getExternalPort();
        this.internalPort = dto.getInternalPort();
    }
}
