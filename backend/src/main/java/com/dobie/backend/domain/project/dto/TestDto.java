package com.dobie.backend.domain.project.dto;

import com.dobie.backend.domain.project.entity.Backend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {

    private String id;
    private String name;

}
