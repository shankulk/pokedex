package com.shankulk.strategy;

import com.shankulk.web.TranslationApiClient;

public class YodaTranslationStrategy extends TranslationStrategy {

    public YodaTranslationStrategy(TranslationApiClient translationApiClient) {
        super(translationApiClient);
    }

    @Override
    String getUrl() {
        return "/yoda.json";
    }
}
