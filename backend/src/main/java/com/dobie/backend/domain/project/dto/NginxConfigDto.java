package com.dobie.backend.domain.project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NginxConfigDto {
    private String domain;
    private List<NginxProxyDto> proxyList;
    private boolean usingHttps;
    private String sslCertificate;
    private String sslCertificateKey;
}
