package com.shankulk.strategy;

import com.shankulk.domain.Translation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
public abstract class TranslationStrategy {

    private final RestTemplate restTemplate;

    protected TranslationStrategy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    abstract String getUrl();

    public String translate(String description) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("text", description);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, getHttpHeaders());

        try {
            ResponseEntity<Translation> response =
                    restTemplate.exchange(getUrl(),
                            HttpMethod.POST,
                            entity,
                            Translation.class);

            return Objects.requireNonNull(response.getBody()).translation();
        } catch (Exception e) {
            log.error("Error occurred while translating description, ", e);
            return description;
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
