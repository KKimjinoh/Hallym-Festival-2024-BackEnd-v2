package com.kkimjinoh.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 관리자용 서버 Swagger springdoc-ui 구성 파일
 */
@Configuration
@Profile("movieadmin")
public class SwaggerMovieAdminConfig {

    @Bean
    public OpenAPI openAdminAPI() {
        final String securitySchemeName = "JWT";

        return new OpenAPI()
                .info(new Info()
                        .title("🛠️ Movie Admin API")
                        .version("v2.0")
                        .description("관리자용 Swagger 명세입니다. <br>" +
                                "👉 <a href='http://localhost:8080/api-docs' target='_blank'>사용자 Swagger 보기</a>")
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

}
