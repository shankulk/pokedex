package com.shankulk.service.impl;

import com.shankulk.domain.Habitat;
import com.shankulk.web.TranslationApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class TranslationServiceImplTest {

    @InjectMocks
    private TranslationServiceImpl translationService;

    @Mock
    private TranslationApiClient translationApiClient;

    @Test
    void caveHabitat_getTranslation_returnsYodaTranslation() {
        //given
        Habitat habitat = new Habitat();
        habitat.setName("cave");

        //when
        translationService.getTranslatedDescription("sample description", habitat);

        //then
        Mockito.verify(translationApiClient).translateString(eq("sample description"), eq("/yoda.json"));
    }

    @Test
    void nonCaveHabitat_getTranslation_returnsShakespeareTranslation() {
        //given
        Habitat habitat = new Habitat();

        //when
        translationService.getTranslatedDescription("sample description", habitat);

        //then
        Mockito.verify(translationApiClient).translateString(eq("sample description"), eq("/shakespeare.json"));
    }

}