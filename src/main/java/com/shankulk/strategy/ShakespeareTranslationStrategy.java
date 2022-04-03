package com.shankulk.strategy;

import com.shankulk.web.TranslationApiClient;

public class ShakespeareTranslationStrategy extends TranslationStrategy {

    public ShakespeareTranslationStrategy(TranslationApiClient translationApiClient) {
        super(translationApiClient);
    }

    @Override
    String getUrl() {
        return "/shakespeare.json";
    }
}
