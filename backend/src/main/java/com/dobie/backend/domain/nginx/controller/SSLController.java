package com.dobie.backend.domain.nginx.controller;

import com.dobie.backend.exception.format.code.ApiResponse;
import com.dobie.backend.exception.format.response.ResponseCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Tag(name = "Nginx 컨트롤러", description = "Nginx Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ssl")
public class SSLController {
    private final ApiResponse response;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("")
    public ResponseEntity<?> getCertificate(@RequestParam (name = "domain")String domain) {
//        String bearerToken = System.getenv("BEARER_TOKEN"); // 예시: 시스템 환경 변수에서 토큰을 가져옴
        // Let's Encrypt ACME v2 API URL
        String leUrl = "https://acme-v02.api.letsencrypt.org/directory";

        // 도메인 인증 요청 페이로드 준비
        String payload = "{\"identifiers\":[{\"type\":\"dns\",\"value\":\"" + domain + "\"}]}";

        try {
            // 새로운 계정 생성 요청
            HttpRequest newAccountRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://acme-v02.api.letsencrypt.org/acme/new-acct"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"termsOfServiceAgreed\": true}"))
                    .build();

            HttpResponse<String> newAccountResponse = httpClient.send(newAccountRequest, HttpResponse.BodyHandlers.ofString());

            if (newAccountResponse.statusCode() != 200) {
                return ResponseEntity.badRequest().body("Failed to create new account: " + newAccountResponse.body());
            }
            // 새로운 계정 생성 후 토큰을 받아옴
            String bearerToken = newAccountResponse.headers().firstValue("Location").orElse(null);
            if (bearerToken == null) {
                return ResponseEntity.badRequest().body("Bearer token not found in response.");
            }
            // Let's Encrypt 서버에 도메인 인증 요청
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://acme-v02.api.letsencrypt.org/acme/new-order"))
                    .header("Content-Type", "application/jose+json")
                    .header("Authorization", "Bearer " + bearerToken)
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 201) {
                return ResponseEntity.badRequest().body("Failed to create new order: " + response.body());
            }

            JsonNode responseData = objectMapper.readTree(response.body());
            String orderUrl = responseData.get("orderUrl").asText();

            // 도메인 확인을 위한 Challenge 정보 가져오기
            request = HttpRequest.newBuilder()
                    .uri(URI.create(orderUrl))
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return ResponseEntity.badRequest().body("Failed to get order details: " + response.body());
            }

            JsonNode orderData = objectMapper.readTree(response.body());
            String authorizationUrl = orderData.get("authorizations").get(0).asText();

            // 도메인 확인을 위한 Challenge 요청
            request = HttpRequest.newBuilder()
                    .uri(URI.create(authorizationUrl))
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return ResponseEntity.badRequest().body("Failed to get authorization details: " + response.body());
            }

            JsonNode authData = objectMapper.readTree(response.body());
            JsonNode challenge = authData.get("challenges").get(0);
            String token = challenge.get("token").asText();
            String keyAuthorization = token + "." + System.getenv("LE_ACCOUNT_KEY");

            // 도메인 확인을 위한 Challenge 수행
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("keyAuthorization", keyAuthorization);

            request = HttpRequest.newBuilder()
                    .uri(URI.create(challenge.get("url").asText()))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return ResponseEntity.badRequest().body("Failed to perform challenge: " + response.body());
            }

            // SSL/TLS 인증서 요청
            JsonNode csrData = objectMapper.readTree("{\"common_name\":\"" + domain + "\"}");
            request = HttpRequest.newBuilder()
                    .uri(URI.create(orderUrl + "/finalize"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(csrData.toString()))
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return ResponseEntity.badRequest().body("Failed to finalize order: " + response.body());
            }

            JsonNode finalizeData = objectMapper.readTree(response.body());
            String certificateUrl = finalizeData.get("certificate").asText();

            // SSL/TLS 개인 키 요청
            String privateKeyUrl = finalizeData.get("privateKey").asText();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(privateKeyUrl))
                    .build();

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return ResponseEntity.badRequest().body("Failed to download private key: " + response.body());
            }

            // 인증서 및 개인 키 저장
            String certificateDirectory = "/sslCertificate/";
            String certificateFilePath = certificateDirectory + domain + "/fullchain.pem";
            String keyFilePath = certificateDirectory + domain + "/privkey.pem";

            try (FileOutputStream certificateFos = new FileOutputStream(certificateFilePath);
                 FileOutputStream keyFos = new FileOutputStream(keyFilePath)) {
                certificateFos.write(response.body().getBytes());
                keyFos.write(response.body().getBytes());
            }

            return ResponseEntity.ok("Certificate and private key downloaded successfully!");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to get certificate: " + e.getMessage());
        }
    }
}
