package com.edutrack.academico.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        
        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT Authentication. Incluye el token en el header: Authorization: Bearer {token}");
        
        Components components = new Components();
        components.addSecuritySchemes(securitySchemeName, securityScheme);
        
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(securitySchemeName);
        
        return new OpenAPI()
                .info(new Info()
                        .title("EduTrack PRO API")
                        .version("1.0.0")
                        .description("API REST para la plataforma de gestión académica EduTrack"))
                .components(components)
                .addSecurityItem(securityRequirement);
    }
}

