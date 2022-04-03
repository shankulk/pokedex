package com.shankulk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {

    @Bean
    @Primary
    public RestTemplate defaultRestTemplate() {
        return new RestTemplate();
    }

    @Bean("pokeApiRestTemplate")
    public RestTemplate pokeApiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("https://pokeapi.co/api/v2/"));
        return restTemplate;
    }

    @Bean("translationRestTemplate")
    public RestTemplate translationRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("https://funtranslations.com/api/"));
        return restTemplate;
    }
}
