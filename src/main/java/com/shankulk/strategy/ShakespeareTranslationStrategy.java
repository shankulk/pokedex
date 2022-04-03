package com.shankulk.strategy;

import org.springframework.web.client.RestTemplate;

public class ShakespeareTranslationStrategy extends TranslationStrategy {

    public ShakespeareTranslationStrategy(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    String getUrl() {
        return "/shakespeare.json";
    }
}
