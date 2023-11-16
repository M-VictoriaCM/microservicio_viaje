package com.microservicio_viaje.microservicio_viaje.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate myRestTemplate() {
        return new RestTemplate();
    }
}
