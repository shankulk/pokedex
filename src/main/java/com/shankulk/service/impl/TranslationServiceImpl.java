package com.shankulk.service.impl;

import com.shankulk.service.TranslationService;
import com.shankulk.strategy.ShakespeareTranslationStrategy;
import com.shankulk.strategy.TranslationStrategy;
import com.shankulk.strategy.YodaTranslationStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslationServiceImpl implements TranslationService {

    private final RestTemplate restTemplate;

    public TranslationServiceImpl(@Qualifier("translationRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getTranslatedDescription(final String description, final String habitat) {
        return getStrategy(habitat).translate(description);
    }

    private TranslationStrategy getStrategy(final String habitat) {
        return "cave".equalsIgnoreCase(habitat)
                ? new YodaTranslationStrategy(restTemplate)
                : new ShakespeareTranslationStrategy(restTemplate);
    }
}
