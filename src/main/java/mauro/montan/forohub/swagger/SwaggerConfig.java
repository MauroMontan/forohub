package mauro.montan.forohub.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {

        Components components = new Components();
        components.addSecuritySchemes("bearer-key",
                new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
        );

        return new OpenAPI()
                .components(components);
    }

}
