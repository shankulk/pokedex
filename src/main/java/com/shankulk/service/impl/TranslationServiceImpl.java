package com.shankulk.service.impl;

import com.shankulk.domain.Habitat;
import com.shankulk.service.TranslationService;
import com.shankulk.strategy.ShakespeareTranslationStrategy;
import com.shankulk.strategy.TranslationStrategy;
import com.shankulk.strategy.YodaTranslationStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class TranslationServiceImpl implements TranslationService {

    private final RestTemplate restTemplate;

    public TranslationServiceImpl(@Qualifier("translationRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getTranslatedDescription(final String description, final Habitat habitat) {
        return getStrategy(habitat).translate(description);
    }

    private TranslationStrategy getStrategy(final Habitat habitat) {
        return Objects.nonNull(habitat) && "cave".equalsIgnoreCase(habitat.getName())
                ? new YodaTranslationStrategy(restTemplate)
                : new ShakespeareTranslationStrategy(restTemplate);
    }
}
