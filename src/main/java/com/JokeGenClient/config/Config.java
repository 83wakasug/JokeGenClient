package com.JokeGenClient.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
@Configuration
public class Config {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://ec2-13-53-167-112.eu-north-1.compute.amazonaws.com/api/v1")
                .build();
    }

    @Bean
    Mapper mappar(){
        return new DozerBeanMapper();
    }
}
