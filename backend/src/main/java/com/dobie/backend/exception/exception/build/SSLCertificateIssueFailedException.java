package com.dobie.backend.exception.exception.build;

import com.dobie.backend.exception.format.response.ErrorCode;
import lombok.Getter;


@Getter
public class SSLCertificateIssueFailedException extends RuntimeException{
    private final ErrorCode errorCode;

    public SSLCertificateIssueFailedException() {
        this.errorCode = ErrorCode.SSL_CERTIFICATE_ISSUE_FAILED;

    }
}
