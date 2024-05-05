package com.dobie.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Dobie API 명세서",
                description = "Dobie 서비스 API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("Bearer")
                                .bearerFormat("JWT"))
                );
    }

    // swagger 그룹 추가
    // 전체보기
    @Bean
    public GroupedOpenApi all() {
        return GroupedOpenApi.builder()
                .group("a. 전체")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi user() {
        return GroupedOpenApi.builder()
                .group("b. 유저")
                .pathsToMatch("/api/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi project() {
        return GroupedOpenApi.builder()
                .group("c. 프로젝트")
                .pathsToMatch("/api/project/**")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi dockerfile() {
        return GroupedOpenApi.builder()
                             .group("d. 도커파일")
                             .pathsToMatch("/dockerfile/**")
                             .build();
    }

    @Bean
    public GroupedOpenApi pathtest() {
        return GroupedOpenApi.builder()
                .group("e. 경로 확인")
                .pathsToMatch("/api/pathtest/**")
                .build();
    }

}
