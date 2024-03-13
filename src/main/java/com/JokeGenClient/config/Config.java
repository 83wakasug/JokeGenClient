package com.JokeGenClient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
@Configuration
public class Config {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("localhost8081/api/v1")
                .build();
    }
}
