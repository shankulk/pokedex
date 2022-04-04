package com.shankulk.web;

import com.shankulk.domain.Translation;
import com.shankulk.dto.TranslationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.shankulk.config.CacheConfig.TRANSLATION;

@Slf4j
@Component
public class TranslationApiClient {

    private final RestTemplate restTemplate;

    public TranslationApiClient(@Qualifier("translationRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(cacheNames = TRANSLATION, key = "#description", unless = "#result == null")
    public String translateString(final String description, final String url) {
        HttpEntity<TranslationRequest> entity = new HttpEntity<>(new TranslationRequest(description), getHttpHeaders());

        try {
            ResponseEntity<Translation> response = restTemplate
                    .exchange(url, HttpMethod.POST, entity, Translation.class);

            return Objects.requireNonNull(response.getBody()).translation();
        } catch (Exception e) {
            log.error("Error occurred while translating description, ", e);
            return description;
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
