package com.shankulk.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TranslationRequest {

    @JsonProperty("text")
    private final String description;

    public TranslationRequest(String description) {
        this.description = description;
    }
}
