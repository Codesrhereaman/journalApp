package net.codesrhereaman.jounalapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI createSwaggerConfig() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("AMAN JI KA PROJECT")
                                .contact(new Contact().email("amanbhardwaj202131@gmail.com"))
                                .description("it is a journalApp to manage the whole journal entries")
                )
                .servers(List.of(new Server().url("http://localhost:8080/").description("server 1"),
                        new Server().url("http://localhost:8081/").description("server 2"))
                )
                .tags(
                        List.of(
                                new Tag().name("Handles Public API's"),
                                new Tag().name("User API's"),
                                new Tag().name("Admin API's"),
                                new Tag().name("Journal Entries API's")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes(
                                "bearerAuth", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")
                        )
                )
                ;
    }

}
