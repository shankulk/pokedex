package com.shankulk.service.impl;

import com.shankulk.domain.Habitat;
import com.shankulk.service.TranslationService;
import com.shankulk.strategy.ShakespeareTranslationStrategy;
import com.shankulk.strategy.TranslationStrategy;
import com.shankulk.strategy.YodaTranslationStrategy;
import com.shankulk.web.TranslationApiClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TranslationServiceImpl implements TranslationService {

    private final TranslationApiClient translationApiClient;

    public TranslationServiceImpl(TranslationApiClient translationApiClient) {
        this.translationApiClient = translationApiClient;
    }

    @Override
    public String getTranslatedDescription(final String description, final Habitat habitat) {
        return getStrategy(habitat).translate(description);
    }

    private TranslationStrategy getStrategy(final Habitat habitat) {
        return Objects.nonNull(habitat) && "cave".equalsIgnoreCase(habitat.getName())
                ? new YodaTranslationStrategy(translationApiClient)
                : new ShakespeareTranslationStrategy(translationApiClient);
    }
}
