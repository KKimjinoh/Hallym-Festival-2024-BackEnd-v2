package com.kkimjinoh.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger springdoc-ui 구성 파일
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("2024 한림대학교 대동제 웹사이트 API")
                .version("v2.0")
                .description("백엔드 API 명세서 입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}

