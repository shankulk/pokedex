package com.shankulk.web;

import com.shankulk.domain.Translation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TranslationApiClientTest {

    @InjectMocks
    private TranslationApiClient translationApiClient;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void exceptionInTranslation_getTranslation_returnsOriginalDescription() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Translation.class)))
                .thenThrow(RuntimeException.class);

        final String originalDescription = "original description";
        final String translatedDescription = translationApiClient.translateString(originalDescription, "/yoda.json");

        Assertions.assertThat(translatedDescription).isEqualTo(originalDescription);
    }

}