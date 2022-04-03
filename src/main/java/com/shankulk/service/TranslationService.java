package com.shankulk.service;

import com.shankulk.domain.Habitat;

public interface TranslationService {

    String getTranslatedDescription(final String description, final Habitat habitat);
}
