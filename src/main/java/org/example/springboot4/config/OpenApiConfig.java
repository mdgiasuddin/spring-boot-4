package org.example.springboot4.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

/**
 * OpenAPI configuration to enable JWT (Bearer) authentication in the Swagger UI.
 * After the application starts, open the Swagger UI and click the "Authorize" button.
 * Use the token in the format: Bearer <your_jwt_token>
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Spring Boot 4 API", version = "v1"),
        security = {@SecurityRequirement(name = "bearerAuth")}
)
@SecurityScheme(
        name = "bearerAuth",
        type = HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = HEADER
)
public class OpenApiConfig {
}
