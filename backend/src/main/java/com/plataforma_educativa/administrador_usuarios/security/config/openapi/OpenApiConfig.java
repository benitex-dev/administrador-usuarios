package com.plataforma_educativa.administrador_usuarios.security.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("API de portal de cursos - Benitex Dev")
                        .version("1.0")
                        .description("Sistema de gestión de cursos, estudiantes y profesores con autenticación a base de roles y permisos. Manejo de la sesión a traves de JWT."))
                // Agrega la configuración de seguridad global a Swagger
                        .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                        .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

    }
}
