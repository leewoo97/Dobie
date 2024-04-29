package com.dobie.backend.domain.nginx.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NginxConfigGetResponseDto {
    private String domain;
    private List<NginxProxyDto> locations;
    private boolean isHttps;
    private String sslCertificate;
    private String sslCertificateKey;

    }
