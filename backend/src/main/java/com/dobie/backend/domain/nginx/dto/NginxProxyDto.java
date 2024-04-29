package com.dobie.backend.domain.nginx.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NginxProxyDto {
    private String location;
    private String serviceName;
    private String port;

}
