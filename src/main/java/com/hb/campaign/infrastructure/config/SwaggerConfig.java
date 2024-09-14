package com.hb.campaign.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customerOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Offers Price")
                        .version("1.0.0"));
    }
}
