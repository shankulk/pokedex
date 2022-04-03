package com.shankulk.strategy;

import com.shankulk.web.TranslationApiClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TranslationStrategy {

    private final TranslationApiClient translationApiClient;

    protected TranslationStrategy(TranslationApiClient translationApiClient) {
        this.translationApiClient = translationApiClient;
    }

    abstract String getUrl();

    public String translate(String description) {
        return translationApiClient.translateString(description, getUrl());
    }

}
