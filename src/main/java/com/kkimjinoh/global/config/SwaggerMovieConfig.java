package com.kkimjinoh.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 사용자용 서버 Swagger springdoc-ui 구성 파일
 */
@Configuration
@Profile("movie")
public class SwaggerMovieConfig {
    @Bean
    public OpenAPI openMovieAPI() {
        return new OpenAPI().info(new Info()
                .title("🎬 Movie User API")
                .version("v2.0")
                .description("사용자용 Swagger 명세입니다. <br>" +
                        "👉 <a href='http://localhost:8082/api-docs' target='_blank'>관리자용 Swagger 보기</a>")
        );
    }
}
