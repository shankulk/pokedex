package com.shankulk.strategy;

import org.springframework.web.client.RestTemplate;

public class YodaTranslationStrategy extends TranslationStrategy {

    public YodaTranslationStrategy(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    String getUrl() {
        return "/yoda";
    }
}
